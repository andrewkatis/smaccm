/*
Copyright (c) 2011, 2013 Rockwell Collins and the University of Minnesota.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
 */

package edu.umn.cs.crisys.smaccm.aadl2rtos.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.Feature;
import org.osate.aadl2.PortCategory;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.DataAccessImpl;
import org.osate.aadl2.impl.DataImplementationImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.DataSubcomponentImpl;
import org.osate.aadl2.impl.DataTypeImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.SubcomponentImpl;
import org.osate.aadl2.impl.ThreadSubcomponentImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionKind;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.*;

public class AadlModelParser {
	private SystemImplementation systemImplementation;
	private SystemInstance systemInstance;

	// containers for AADL AST objects
	private ArrayList<ThreadTypeImpl> threadTypeImplList;
	// private Set<SystemImpl> systemImplList = new HashSet<SystemImpl>();
	
	// Instance objects
  private ArrayList<ComponentInstance> threadInstanceList;
  private ArrayList<ConnectionInstance> connectionInstances;
	// private ArrayList<InstanceObject> abstractInstances;
  //private ArrayList<InstanceObject> busInstances;
//private ArrayList<InstanceObject> dataInstances;
//private ArrayList<InstanceObject> deviceInstances;
//private ArrayList<InstanceObject> memoryInstances;
//private ArrayList<InstanceObject> processInstances;
//private ArrayList<InstanceObject> processorInstances;
//private ArrayList<InstanceObject> subprogramInstances;
//private ArrayList<InstanceObject> subprogramGroupInstances;
//private ArrayList<InstanceObject> systemInstances;
//private ArrayList<InstanceObject> threadGroupInstances;
//private ArrayList<InstanceObject> virtualBusInstances;
//private ArrayList<InstanceObject> virtualProcessorInstances;

	
	// Our AST containers

	// TODO: Thread names do not have to be globally unique: 
	private Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap;
	private Map<DataSubcomponentImpl, SharedData> sharedDataMap = 
	    new HashMap<DataSubcomponentImpl, SharedData>();	
	private HashMap<PortImpl, DataPort> portMap = new HashMap<PortImpl, DataPort>();
	
	// Instance map
	private Map<ComponentInstance, ThreadInstance> threadInstanceMap;
	
	//
	private Model model;
	
	private Logger logger;
	
	// Model constructor
	public AadlModelParser(SystemImplementation sysimpl, SystemInstance si, Model model, Logger logger) {
		this.systemImplementation = sysimpl;
		this.systemInstance = si;
		this.logger = logger;
		this.model = model;
		
		// re-init the counters.
		Connection.init();
		ThreadInstance.init();
		
		threadTypeImplList = new ArrayList<ThreadTypeImpl>();
		threadInstanceList = new ArrayList<ComponentInstance>();
		threadInstanceMap = new HashMap<ComponentInstance, ThreadInstance>();
		
		// Connection instances
		connectionInstances = new ArrayList<ConnectionInstance>();

		// Instance objects

		// Parse existing AADL model
		findThreadTypeImpls(systemImplementation);
		findTopLevelComponentInstances(systemInstance);
		findAllSourceTexts(systemImplementation); 
		
		// create the SystickIRQ value, if it exists.
		try {
		  this.model.generateSystickIRQ = PropertyUtils.getBooleanValue(systemImplementation, ThreadUtil.GENERATE_SCHEDULER_SYSTICK_IRQ);
		} catch (Exception e) {}
		
		// Initialize thread implementations
		initializeThreadImplMap();

		// Initialize connections
		initializeConnections();

		// initialize shared data
		
		// Initialize Periodic Dispatcher
		initializeThreadCalendar(); 
		//addPeriodicISR(); 
		
		// grab all files referenced in the model.
		initializeFiles();
		initializeLegacyIRQs();
		
		// Harvest model type data
		harvestModelTypeData();
		
		// initialize the model thread and shared data lists.
		this.model.threadImplementationList = new ArrayList<ThreadImplementation>(this.threadImplementationMap.values());
		this.model.sharedDataList = new ArrayList<SharedData>(this.sharedDataMap.values());
	}

	void addDispatcher(PortImpl port, ThreadImplementation ti) {
	  
	}
	
	InputEventPort addInputEventPort(PortImpl port, Type datatype, ThreadImplementation ti) {
    int queueSize = PortUtil.getQueueSize(port); 
    InputEventPort iep = new InputEventPort(port.getName(), new UnitType(), ti, queueSize);
    ti.addInputEventPort(iep);
    List<String> entrypoints = ThreadUtil.getComputeEntrypointList(port);
    String file = Util.getStringValueOpt(port,ThreadUtil.SOURCE_TEXT);
    if (entrypoints != null) {
      ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
      for (String s: entrypoints) {
          ExternalHandler eh = new ExternalHandler(s, file);
          ehl.add(eh);
      }
      InputEventDispatcher disp = new InputEventDispatcher(ti, ehl, iep);
      ti.addDispatcher(disp);
    } else {
      logger.warn("Warning: event port: " + port.getName() + " does not have a compute entrypoint and will not be dispatched.");
    }
    return iep;
	}
	
	void addPort(PortImpl port, ThreadImplementation ti) {
	  DataPort dp = null;
	  if (port.getDirection() == DirectionType.IN_OUT) {
	    logger.error("Gentlemen do not process IN_OUT ports");
	    throw new Aadl2RtosException("Gentlemen do not process IN_OUT ports");
	  }
    Type datatype = getDataType(port);
    if (port.getCategory() == PortCategory.DATA) {
      if (port.getDirection() == DirectionType.IN) {
        InputDataPort idp = new InputDataPort(port.getName(), datatype, ti);
        ti.addInputDataPort(idp);
        dp = idp;
      } else {
        OutputDataPort odp = new OutputDataPort(port.getName(), datatype, ti);
        ti.addOutputDataPort(odp);
        dp = odp;
      }
    } else if (port.getCategory() == PortCategory.EVENT) {
      if (port.getDirection() == DirectionType.IN) {
        dp = addInputEventPort(port, new UnitType(), ti);
      } else {
        OutputEventPort oep = new OutputEventPort(port.getName(), new UnitType(), ti);
        ti.addOutputEventPort(oep);
        dp = oep;
      }
    } else if (port.getCategory() == PortCategory.EVENT_DATA) {
      if (port.getDirection() == DirectionType.IN) {
        dp = addInputEventPort(port, datatype, ti);
      } else {
        OutputEventPort oep = new OutputEventPort(port.getName(), datatype, ti);
        dp = oep;
        ti.addOutputEventDataPort(oep);
      }
    }
    portMap.put(port, dp);
	}
	
	// here is where the performance bottleneck is.
	private void initializeThreadImplMap() {
		threadImplementationMap = new HashMap<ThreadTypeImpl, ThreadImplementation>(threadTypeImplList.size());

		// map component instances to implementations.
		HashMap<ThreadTypeImpl, List<ComponentInstance>> 
			threadImplToInstanceMap = new HashMap<ThreadTypeImpl, List<ComponentInstance>>(); 
		for (ComponentInstance co : threadInstanceList) {
			// String threadType = co.getComponentClassifier().getName().toString();
			ThreadTypeImpl threadType = (ThreadTypeImpl)co.getComponentClassifier(); 
			if (!threadImplToInstanceMap.containsKey(threadType)) {
				threadImplToInstanceMap.put(threadType, new ArrayList<ComponentInstance>());
			} 
			threadImplToInstanceMap.get(threadType).add(co);
		}

		for (ThreadTypeImpl tti : threadTypeImplList) {
			//String threadImplName = tti.getName();
			
			if (ThreadUtil.getLegacyValue(tti)) {
				LegacyThreadImplementation lti = constructLegacyThreadImplementation(tti); 
				this.model.legacyThreadList.add(lti);
				this.model.legacyMutexList.addAll(lti.getLegacyMutexes());
				this.model.legacySemaphoreList.addAll(lti.getLegacySemaphores());
			} else {
				ThreadImplementation threadImplementation = 
				    constructThreadImplementation(tti); 
				if (!threadImplToInstanceMap.containsKey(tti)) {
					throw new Aadl2RtosException("Unable to find any thread instances for implementation: " + tti.getName()) ; 
				}
				else {
					for (ComponentInstance co: threadImplToInstanceMap.get(tti)) {
						ThreadInstance instance = new ThreadInstance(threadImplementation);
						threadImplementation.addThreadInstance(instance);
						this.threadInstanceMap.put(co, instance);
					}
				}
			
        // If ISR thread, create new ISR, port, and connections to receive from ISR.
        /*  
          String signal = threadImplementation.getSmaccmSysSignalOpt();
          String handler = threadImplementation.getISRHandlerName();
          InterruptServiceRoutine isr = 
               new InterruptServiceRoutine(signal, handler);
          this.model.isrList.add(isr);
          List<String> handlerList = new ArrayList<String>();
          handlerList.add(handler);
          MyPort destPort = new MyPort("smaccm_isr_input_port", 
        		  handlerList, threadImplementation.getFileNames().get(0), null, 
              null, null, threadImplementation, MyPort.PortType.INPUT_EVENT_PORT);
          threadImplementation.addPort(destPort);
          isr.setDestinationPort(destPort);
          for (ThreadInstance ti: threadImplementation.getThreadInstanceList()) {
            Connection c_fake = new Connection(null, ti, isr.getOutputPort(), destPort);
            this.model.connectionList.add(c_fake);
            destPort.addConnection(c_fake);
            isr.addThreadInstance(ti);
          }
        }
        */ 
				// Find and add thread ports.
				EList<Feature> features = tti.getAllFeatures(); 
				for (Feature f: features) {
				  if (f instanceof PortImpl) {
				    PortImpl portImpl = (PortImpl)f;
				    addPort(portImpl, threadImplementation); 
				  } else if (f instanceof DataAccessImpl) {
				    // TODO: Something here.  Fucking really.
				  }
				  
				}
				threadImplementationMap.put(tti, threadImplementation);
			}
		}
	}

	private LegacyThreadImplementation constructLegacyThreadImplementation(ThreadTypeImpl tti) {
    // For thread base, 
    String name = tti.getName().toLowerCase();
    int priority = ThreadUtil.getPriority(tti);
    int stackSize = ThreadUtil.getStackSizeInBytes(tti);
    List<String> fileNames = Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT);
    List<String> legacyMutexList;
    List<String> legacySemaphoreList;
    String generatedEntrypoint;
    try { 
      legacyMutexList = (ArrayList<String>) ThreadUtil.getLegacyMutexList(tti);
      legacySemaphoreList = (ArrayList<String>) ThreadUtil.getLegacySemaphoreList(tti);
      generatedEntrypoint = Util.getStringValue(tti, ThreadUtil.LEGACY_ENTRYPOINT);
      return new LegacyThreadImplementation(name, priority, stackSize, fileNames, 
          legacyMutexList, legacySemaphoreList, generatedEntrypoint);
    } catch (Exception e) {
      throw new Aadl2RtosException("Error: Legacy threads must have a SMACCM_SYS::Legacy_Entrypoint property defined");
    }
	}
	
	private ThreadImplementation constructThreadImplementation(ThreadTypeImpl tti) {
	  // For thread base, 
    String name = tti.getName().toLowerCase();
    int priority = ThreadUtil.getPriority(tti);
    int stackSize = ThreadUtil.getStackSizeInBytes(tti);
	  List<String> fileNames = Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT);
	  
	  String generatedEntrypoint = tti.getFullName();
	  String smaccmSysSignalOpt = Util.getStringValueOpt(tti, ThreadUtil.SMACCM_SYS_SIGNAL_NAME);
    boolean isrThread = (smaccmSysSignalOpt != null);
    String isrHandlerName = null;
    if (isrThread) {
      isrHandlerName = Util.getStringValueOpt(tti, ThreadUtil.ISR_HANDLER);
      if (isrHandlerName == null) {
        throw new Aadl2RtosException("Error: ISR Thread: "+ tti.getFullName() + " requires ISR_handler property");
      }
    }
    String entryPointSourceText = (String) Util.getStringValueOpt(tti,
        ThreadUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
    String entryPointFile = fileNames.get(0);

    EnumerationLiteral dispatchProtocol; 
    
    try {
      dispatchProtocol = ThreadUtil.getDispatchProtocol(tti);
    } catch (Exception e) {
      throw new Aadl2RtosException(
          "Dispatch protocol not found for thread: " + name);
    }
    
    int period = 0;
    List<String> entrypointNameList = null; 
    if ((dispatchProtocol != null)
        && (dispatchProtocol.getName().equalsIgnoreCase("Periodic") || dispatchProtocol
            .getName().equalsIgnoreCase("Hybrid"))) {
      // if periodic or hybrid, thread should have a period and a
      // compute entrypoint
      try {
        period = (int) PropertyUtils.getIntegerValue(tti, ThreadUtil.PERIOD);
        entrypointNameList = 
            ThreadUtil.getComputeEntrypointList(tti); 

      } catch (Exception e) {
        throw new Aadl2RtosException(
            "For thread "
                + name
                + " with dispatch protocol "
                + (dispatchProtocol.toString())
                + " properties: 'Period', 'Compute_Entrypoint_Source_Text', and 'Source_Text' are required.");
      }
    }
    
    return new ThreadImplementation(name, priority, stackSize, fileNames, 
        generatedEntrypoint, isrThread, smaccmSysSignalOpt, isrHandlerName, 
        entryPointSourceText, entryPointFile, dispatchProtocol.getName(), period, 
        entrypointNameList);
	}
	
	
	private void initializeFiles() {
	  
	  // Get dispatcher file names.
	  for (ThreadImplementation i: this.getThreadImplementationMap().values()) {
	    for (Dispatcher d: i.getDispatcherList()) {
	      for (ExternalHandler h: d.getExternalHandlerList()) {
	        if (h.getHandlerFileName() != null) {
	          this.model.sourceFiles.add(h.getHandlerFileName());
	        }
	      }
	    }
	    this.model.sourceFiles.addAll(i.getFileNames());
	  }
	  
    // create initializer handler, if it exists.
    List<String> topLevelFileNames = 
        Util.getSourceTextListOpt(this.systemImplementation, ThreadUtil.SOURCE_TEXT);
 
		if (topLevelFileNames != null) {
			for (String s : topLevelFileNames) {
				logger.status("Referenced File: " + s);

				if (s.endsWith(".a")) {
					this.model.libraryFiles.add(s);
				} else {
					this.model.sourceFiles.add(s);
				}
			}
		}
	}
	
	private void initializeLegacyIRQs() {
	  List<String> irqStrings = ThreadUtil.getLegacyIRQList(this.systemImplementation);
	  
	  if (irqStrings.size() % 2 != 0) {
	    throw new Aadl2RtosException("Error: legacy IRQ property should be list of size 2*n, where each element of n is a signal_name, handler_name pair");
	  }
	  Iterator<String> it1 = irqStrings.iterator();
	  while (it1.hasNext()) {
	    String name = it1.next();
	    String handlerName = it1.next();
	    LegacyExternalISR irq = new LegacyExternalISR(name, handlerName);
	    this.model.legacyExternalIRQList.add(irq);
	  }
	  
	  List<String> irqEventStrings = ThreadUtil.getLegacyIRQEventList(this.systemImplementation);
	  if (irqEventStrings.size() % 3 != 0) {
	    throw new Aadl2RtosException("Error: legacy IRQ Event property should be a list of size 3*n, where each element of n is a IRQ_event_name, task_name, sig_set triple");
	  }
	  it1 = irqEventStrings.iterator();
	  while (it1.hasNext()) {
	      String eventName = it1.next();
	      String taskName = it1.next();
	      String signalSetString = it1.next(); 
	      int signal;
	      try {
		      signal = Integer.parseInt(signalSetString);
		  } catch (NumberFormatException e) {
		      throw new Aadl2RtosException("Error: legacy IRQ event property: third argument of triple not a number.");
		  }
		  LegacyIRQEvent evt = new LegacyIRQEvent(eventName, taskName, signal);
		  this.model.legacyIRQEventList.add(evt);
	  }
	  
	  List<String> externalIrqStrings = ThreadUtil.getExternalIRQList(this.systemImplementation);
	  if (externalIrqStrings.size() %2 != 0) {
		  throw new Aadl2RtosException("Error: External IRQ property should be a list of size 2*n, where each element of n is a external_irq, irq_number pair");
	  }
	  it1 = externalIrqStrings.iterator();
	  while (it1.hasNext()) {
		  String externIrqName = it1.next();
		  String externIrqNumberString = it1.next();
		  int externIrqNumber;
		  try {
			  externIrqNumber = Integer.parseInt(externIrqNumberString);
		  } catch (NumberFormatException e) {
			  throw new Aadl2RtosException("Error: External IRQ property: second argument of pair is not a number");
		  }
		  ExternalIRQ irq = new ExternalIRQ(externIrqName, externIrqNumber);
		  this.model.externalIRQList.add(irq);
	  }
	}
	
	private void initializeConnections() {
		try {
			for (ConnectionInstance ci : connectionInstances) {
			  if (ci.getKind() == ConnectionKind.PORT_CONNECTION) {
  				PortImpl destPortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getDestination());
  				PortImpl sourcePortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getSource());
  
  				if ((destPortImpl == null) || (sourcePortImpl == null)) {
  				  throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
  				      " one of source/destination port implementations was not defined ('null').");
  				}
  				
  				DataPort sPort = portMap.get(sourcePortImpl);
          DataPort dPort = portMap.get(destPortImpl);
  				if (sPort == null || dPort == null || 
  				    !(sPort instanceof OutputPort) || 
  				    !(dPort instanceof InputPort)) {
            throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
                " one of source/destination thread port ASTs was not defined ('null').");
  				}
          OutputPort sourcePort = (OutputPort)sPort;
          InputPort destPort = (InputPort)dPort;
  								
  				// find source and destination thread instances.
  				ComponentInstance aadlSrcThreadInstance = ci.getSource().getComponentInstance(); 
  				ComponentInstance aadlDstThreadInstance = ci.getDestination().getComponentInstance(); 
  				ThreadInstance srcThreadInstance = this.threadInstanceMap.get(aadlSrcThreadInstance); 
  				ThreadInstance dstThreadInstance = this.threadInstanceMap.get(aadlDstThreadInstance);
  				if ((srcThreadInstance == null) || (dstThreadInstance == null)) {
            throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
                " one of source/destination thread instances was not defined ('null').");
          }
  				
  				// create connection object and connect to ports and thread instances.
  				Connection conn = new Connection(srcThreadInstance, dstThreadInstance, sourcePort, destPort);
  				srcThreadInstance.addIsSrcOfConnection(conn);
  				dstThreadInstance.addIsDstOfConnection(conn);
  				sourcePort.addConnection(conn);
  				destPort.addConnection(conn);
  				this.model.connectionList.add(conn);
	      } else if (ci.getKind() == ConnectionKind.ACCESS_CONNECTION) {
          DataAccessImpl destAccessImpl = 
              PortUtil.getDataAccessImplFromConnectionInstanceEnd(ci.getDestination());
          DataSubcomponentImpl srcDataComponent = 
              PortUtil.getDataSubcomponentImplFromConnectionInstanceEnd(ci.getSource());
          SharedData sharedData;
          if (this.sharedDataMap.containsKey(srcDataComponent)) {
            sharedData = this.sharedDataMap.get(srcDataComponent);
          } else {
            sharedData = new SharedData(srcDataComponent.getName(), getDataType(srcDataComponent));
            this.sharedDataMap.put(srcDataComponent, sharedData);
          }
          // find destination thread instance and implementation.
          ComponentInstance aadlThreadInstance = ci.getDestination().getComponentInstance(); 
          ThreadInstance threadInstance = this.threadInstanceMap.get(aadlThreadInstance); 
          if (threadInstance == null) {
            throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
                "the accessing thread instance was not defined ('null').");
          }
          ThreadImplementation threadImpl = threadInstance.getThreadImplementation();
          String commprimFnNameOpt = Util.getStringValueOpt(destAccessImpl, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
          String commprimHeaderNameOpt = Util.getStringValueOpt(destAccessImpl, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
          EnumerationLiteral access = null;
          try {
            access = PropertyUtils.getEnumLiteral(destAccessImpl, ThreadUtil.ACCESS_RIGHT);
          } catch (Exception e) {
            throw new Aadl2RtosException("Required property 'Access_Right' not found for data access: " + destAccessImpl.getName());
          }
          
          SharedDataAccessor.AccessType accessType;
          if (access.getName().equalsIgnoreCase("write_only")) {
            accessType = SharedDataAccessor.AccessType.WRITE;
          } else if (access.getName().equalsIgnoreCase("read_only")) {
            accessType = SharedDataAccessor.AccessType.READ;
          } else if (access.getName().equalsIgnoreCase("read_write")) {
            accessType = SharedDataAccessor.AccessType.READ_WRITE;
            throw new Aadl2RtosException("Required property 'Access_Right' has value: " + access.getName() + 
                " which is unsupported: currently only write_only and read_only are supported.");
          } else {
            throw new Aadl2RtosException("Required property 'Access_Right' has value: " + access.getName() + 
                " which is unsupported.");
          }
          
          SharedDataAccessor sharedDataAccess = 
              new SharedDataAccessor(threadImpl, destAccessImpl.getName(), 
                  sharedData, accessType, 
                  commprimFnNameOpt, commprimHeaderNameOpt);
          
          threadImpl.addSharedDataAccessor(sharedDataAccess); 
	      } else {
	        throw new Aadl2RtosException("ConnectionKind: " + ci.getKind() + " is not currently supported by SystemBuild.");
	      }
			}
		} catch (Exception e) {
			 throw new Aadl2RtosException("Unexpected exception: " + e.toString() + " in AadlModelParser::initializeConnections.");
		}
	}

	private void initializeThreadCalendar() {
	  
	  for (ThreadImplementation ti: this.threadImplementationMap.values()) {
	    for (Dispatcher d: ti.getDispatcherList()) {
	      if (d instanceof PeriodicDispatcher) {
	        PeriodicDispatcher pd = (PeriodicDispatcher)d;
          this.model.threadCalendar.addPeriodicDispatcher(pd);
	      }
	    }
	  }
	}
	
	/*
	private void addPeriodicISR() {
	  if (this.calendar.hasDispatchers()) {
	    this.isrList.
	  }
	}
	*/
	
	private Type getDataType(PortImpl portImpl) {
	  Type dataType = null;
    DataClassifier classifier = (DataClassifier) portImpl.getClassifier();
    // String dcName = Util.normalizeAadlName(classifier);

//    if (!this.model.astTypes.containsKey(dcName)) {
//      System.out.println("Type not found: " + dcName + "\n");
//    } 
    dataType = lookupType(classifier);

    return dataType;
	}

	private Type getDataType(DataSubcomponentImpl dataImpl) {
	  DataClassifier classifier = (DataClassifier) dataImpl.getClassifier();
	  return lookupType(classifier);
	}
	
	/*	
	private Type getDataType(ConnectionInstanceEnd endPoint) {
	  NamedElement no = endPoint.getInstantiatedObjects().get(0);
		if (no instanceof PortImpl) {
		  return getDataType((PortImpl)no);
		}
		else
		   throw new Aadl2RtosException("For code generation, connections must terminate at ports.  Actual type: " + no);
	}
*/
	
	private void findAllSourceTexts(Element elem) {
/*	  if (elem instanceof SubcomponentImpl) {
	    SubcomponentImpl sub = (SubcomponentImpl) elem;
	    ComponentImplementation impl = sub.getComponentImplementation();
	    Classifier classifier = null; 
	    
	    if (elem instanceof SystemImpl) {
	      SystemImpl simpl = (SystemImpl) sub;
	    }
	  } */
	}
	
	private void findThreadTypeImpls(Element elem) {
		if (elem instanceof SubcomponentImpl) {
			SubcomponentImpl sub = (SubcomponentImpl) elem;
			ComponentImplementation impl = sub.getComponentImplementation();
			Classifier classifier = null;

			if (sub instanceof ThreadSubcomponentImpl) {
				ThreadSubcomponentImpl tsub = (ThreadSubcomponentImpl) sub;
				classifier = tsub.getClassifier();

				if (classifier instanceof ThreadTypeImpl) {
					ThreadTypeImpl tti = (ThreadTypeImpl) classifier;
					threadTypeImplList.add(tti);
					return;
				} else {
					String error = "In findThreads: classifier for ThreadSubcomponentImpl is not a ThreadTypeImpl.n";
					System.out.println(error);
				}
			} else if (impl != null) {
				findThreadTypeImpls(impl);
			}
		}
		for (Element child : elem.getChildren()) {
			findThreadTypeImpls(child);
		}
	}

	private void findTopLevelComponentInstances(SystemInstance top) {
		List<ComponentInstance> components = top.getAllComponentInstances();
		List<ConnectionInstance> connections = top.getAllConnectionInstances();

		for (ConnectionInstance ci : connections) {
			connectionInstances.add(ci);
		}

		for (ComponentInstance ci : components) {
			ComponentCategory category = ci.getCategory();

			if (category == ComponentCategory.THREAD) {
				threadInstanceList.add(ci);
			}
//			if (category == ComponentCategory.ABSTRACT) {
//				abstractInstances.add(ci);
//			} else if (category == ComponentCategory.BUS) {
//				busInstances.add(ci);
//			} else if (category == ComponentCategory.DATA) {
//				dataInstances.add(ci);
//			} else if (category == ComponentCategory.DEVICE) {
//				deviceInstances.add(ci);
//			} else if (category == ComponentCategory.MEMORY) {
//				memoryInstances.add(ci);
//			} else if (category == ComponentCategory.PROCESS) {
//				processInstances.add(ci);
//			} else if (category == ComponentCategory.PROCESSOR) {
//				processorInstances.add(ci);
//			} else if (category == ComponentCategory.SUBPROGRAM) {
//				subprogramInstances.add(ci);
//			} else if (category == ComponentCategory.SUBPROGRAM_GROUP) {
//				subprogramGroupInstances.add(ci);
//			} else if (category == ComponentCategory.SYSTEM) {
//				systemInstances.add(ci);
//			} else if (category == ComponentCategory.THREAD_GROUP) {
//				threadGroupInstances.add(ci);
//			} else if (category == ComponentCategory.VIRTUAL_BUS) {
//				virtualBusInstances.add(ci);
//			} else if (category == ComponentCategory.VIRTUAL_PROCESSOR) {
//				virtualProcessorInstances.add(ci);
//			}
		}
	}

  public Map<DataSubcomponentImpl, SharedData> getDataMap() {
    return this.sharedDataMap;
  }

  public Map<ThreadTypeImpl, ThreadImplementation> getThreadImplementationMap() {
		return threadImplementationMap;
	}
	
  
	public Map<String, Type> getAstTypes() {
		return this.model.astTypes;
	}

	public void harvestModelTypeData() {
		// Collect thread data types
		for (ThreadTypeImpl t : this.threadTypeImplList) {
			collectDataTypes(t);
		}

		// create internal ast types from the AADL types
		for (DataClassifier dc : this.model.dataTypes) {
			Type t = createAstType(dc);
			if (!t.isBaseType()) {
				this.model.astTypes.put(Util.normalizeAadlName(dc), t);
			}
		}
		for (Type t : this.model.astTypes.values()) {
			t.init(this.model.astTypes);
		}
	}

	// find the data types associated with some "top level" element.
	public void collectDataTypes(Element elem) {
		if (elem instanceof DataPortImpl) {
			DataPortImpl dpi = (DataPortImpl) elem;
			Classifier dpiClass = dpi.getClassifier();

			if (dpiClass instanceof DataTypeImpl || dpiClass instanceof DataImplementationImpl) {
				this.model.dataTypes.add((DataClassifier) dpiClass);
			}
		}
		for (Element child : elem.getChildren()) {
			collectDataTypes(child);
		}
	}

	public Type lookupType(DataClassifier dc) {
		String dcName = Util.normalizeAadlName(dc);
		Type ty = createAstType(dc);
		if (this.model.astTypes.containsKey(dcName)) {
			return new IdType(dcName, this.model.astTypes.get(dcName));
		} else {
			return ty;
		}
	}

	// what about the recursive aspect of this?
	// So what we want is: for each subcomponent, record the field id and the
	// type name.
	public Type createAstType(DataClassifier dc) {
		if (dc == null) {
			return null;
		}
		String qualifiedName = dc.getQualifiedName();
		String normalizedName = Util.normalizeAadlName(qualifiedName);

		if (this.model.astTypes.containsKey(normalizedName)) {
			return this.model.astTypes.get(normalizedName);
		}

		// base types defined by the data modeling annex
		if ("Base_Types::Boolean".equals(qualifiedName)) {
			return new BoolType();
		} else if ("Base_Types::Integer_8".equals(qualifiedName)) {
			return new IntType(8, true);
		} else if ("Base_Types::Integer_16".equals(qualifiedName)) {
			return new IntType(16, true);
		} else if ("Base_Types::Integer_32".equals(qualifiedName)) {
			return new IntType(32, true);
		} else if ("Base_Types::Integer_64".equals(qualifiedName)) {
			return new IntType(64, true);
		} else if ("Base_Types::Unsigned_8".equals(qualifiedName)) {
			return new IntType(8, false);
		} else if ("Base_Types::Unsigned_16".equals(qualifiedName)) {
			return new IntType(16, false);
		} else if ("Base_Types::Unsigned_32".equals(qualifiedName)) {
			return new IntType(32, false);
		} else if ("Base_Types::Unsigned_64".equals(qualifiedName)) {
			return new IntType(64, false);
		} else if ("Base_Types::Float".equals(qualifiedName)) {
			return new RealType(FloatEnum.SINGLE);			
		} else if ("Base_Types::Float_32".equals(qualifiedName)) {
			return new RealType(FloatEnum.SINGLE);
		} else if ("Base_Types::Float_64".equals(qualifiedName)) {
			return new RealType(FloatEnum.DOUBLE);
		} else if ("Base_Types::Character".equals(qualifiedName)) {
			throw new Aadl2RtosException("Character types are currently unsupported");
		} else if ("Base_Types::String" == qualifiedName) {
			throw new Aadl2RtosException("String types are currently unsupported");
		} else if (dc instanceof DataTypeImpl) {
		  DataTypeImpl dti = (DataTypeImpl)dc;
			EnumerationLiteral el = Util.getDataRepresentationName(dti);
			DataClassifier childDc = Util.getBaseType(dti);
		  int size = Util.getDimension(dti);
			Type childElem = createAstType(childDc); 
			if ((el.getName()).equalsIgnoreCase("Array")) {
			  return new ArrayType(childElem, size);
			} else {
			  throw new Aadl2RtosException("Examining type: " + dc.getFullName() + 
			      " found unexpected representation type: '"+ el.getName() + "'; expecting 'Array'.");
			}
		} else if (dc instanceof DataImplementationImpl) {
			DataImplementationImpl dii = (DataImplementationImpl) dc;
			EList<DataSubcomponent> subcomponents = dii.getOwnedDataSubcomponents();
			if (subcomponents.isEmpty()) {
				
				// check if array type.
				EnumerationLiteral el = Util.getDataRepresentationName(dii);
				DataClassifier childDc = Util.getBaseType(dii);
				int size = Util.getDimension(dii);
				Type childElem = createAstType(childDc); 
				if ((el.getName()).equalsIgnoreCase("Array")) {
				    ArrayType at = new ArrayType(childElem, size); 
				    this.model.astTypes.put(normalizedName, at);
				    return at;
				} else {
				   throw new Aadl2RtosException("Examining type: " + dc.getFullName() + 
				      " found unexpected representation type: '"+ dc.getName() + "'; expecting 'Array'.");
				}
			}
			else {
				RecordType rt = new RecordType();
				for (DataSubcomponent c : subcomponents) {
					Classifier subClass = c.getClassifier();
					if (subClass instanceof DataClassifier) {
						Type subType = createAstType((DataClassifier) subClass);
						rt.addField(c.getName(), subType);
					} else {
						throw new Aadl2RtosException(
								"In createAstType: Subcomponent is not a data classifier");
					}
				}
				this.model.astTypes.put(normalizedName, rt);
				return rt;
			}
		} else {
			throw new Aadl2RtosException(
					"In createAstType: data classifier is not data type or data implementation");
		}
	}

  
}
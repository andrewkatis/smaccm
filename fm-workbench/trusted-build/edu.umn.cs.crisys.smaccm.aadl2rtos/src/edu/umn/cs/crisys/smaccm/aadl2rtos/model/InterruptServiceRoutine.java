/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Whalen
 *
 */
public class InterruptServiceRoutine {

  String signalName;
  String handlerName;
//  MyPort outputServiceRoutinePort;
//  MyPort destPort;
  public enum ISRType {InThreadContextISR, SignalingISR} ; 
  ISRType isrType = ISRType.SignalingISR; 
  
  List<ThreadInstance> destThreads = new ArrayList<ThreadInstance>();
  
  /*
   * TODO: We need to refactor the port hierarchy so that we can create an 
   * OutputInterruptServiceRoutine port.  It is quite different from other 
   * ports.   
   * 
   */
  
  public InterruptServiceRoutine(String signalName, String handlerName) {
    this.signalName = signalName; 
    this.handlerName = handlerName;
    
   // System.out.println("Signal Name: " + signalName);
    //System.out.println("Handler Name: " + handlerName);
//    this.outputServiceRoutinePort = new MyPort(this); 
  }
/*  
  public MyPort getOutputPort() {
    return this.outputServiceRoutinePort;
  }
  
  public void setDestinationPort(MyPort destPort) {
    this.destPort = destPort;
  }
  
  public MyPort getDestinationPort() {
    return this.destPort;
  }
*/
  
  public void addThreadInstance(ThreadInstance ti) {
    this.destThreads.add(ti);
  }
  
  public List<ThreadInstance> getThreadInstances() {
    return this.destThreads;
  }
  
  public String getSignalName() {
    return signalName;
  }
  
  public ISRType getISRType() {
	  return isrType;
  }
  
  public String getHandlerName() {
	  return "smaccm_" + handlerName;
  }
  
  public String getTowerHandlerName() {
	  return handlerName;
  }
  
  public String getIrqSignalName() {
    return ("smaccm_irq_" + getSignalName()).toLowerCase();
  }
  
  public String getIrqSignalSetDefine() {
    return ("SIGNAL_SET_" + this.getIrqSignalName()).toUpperCase(); 
  }
  
  public String getIrqSignalDefine() {
    return ("IRQ_EVENT_ID_" + this.getIrqSignalName()).toUpperCase();
  }
}

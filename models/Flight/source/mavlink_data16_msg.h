/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#ifndef __MAVLINK_DATA16_MSG_H__
#define __MAVLINK_DATA16_MSG_H__
#ifdef __cplusplus
extern "C" {
#endif
#include "ivory.h"
#include "mavlinkSendModule.h"
#include "mavlink_pack_ivory.h"
struct data16_msg {
    uint8_t data16_type;
    uint8_t len;
    uint8_t data16[16U];
} __attribute__((__packed__));
void mavlink_data16_msg_send(const struct data16_msg* n_var0, uint8_t* n_var1,
                             uint8_t n_var2[80U]);
void mavlink_data16_unpack(struct data16_msg* n_var0, const uint8_t* n_var1);

#ifdef __cplusplus
}
#endif
#endif /* __MAVLINK_DATA16_MSG_H__ */
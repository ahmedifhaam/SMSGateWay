package com.xelvias.imsms.Dao;

import com.xelvias.imsms.Models.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;


public interface MessageLogDao extends JpaRepository<MessageLog,Long> {
    List<MessageLog> findAllByDateBetween(Date startDate, Date endDate);

    List<MessageLog> findTop100ByOrderByDateDesc();
//    List<MessageLog> findAllByUserAndMessageMaskAndMessageStatusAndMessagePhonenumber(@Nullable String user,
//                                                                                      @Nullable String messageMask,
//                                                                                      @Nullable String messageStatus,
//                                                                                      @Nullable String messagePhonenumber);
//
//
//
//
//
//
//
//
//    List<MessageLog> findAllByUser(@Nullable String user);
//    List<MessageLog> findAllByMessageMask( @Nullable String messageMask);
//    List<MessageLog> findAllByMessagePhonenumber( @Nullable String messagePhonenumber);
//    List<MessageLog> findAllByStatus(@Nullable String messageStatus);
//
//    List<MessageLog> findAllByUserAndMessageMask(@Nullable String user, @Nullable String messageMask);
//    List<MessageLog> findAllByUserAndMessageStatus(@Nullable String user,@Nullable String messageStatus);
//    List<MessageLog> findAllByUserAndMessagePhonenumber(@Nullable String user, @Nullable String messagePhonenumber);
//
//    List<MessageLog> findAllByMessageMaskAndMessageStatus(@Nullable String messageMask,@Nullable String messageStatus);
//    List<MessageLog> findAllByMessageMaskAndMessagePhonenumber(@Nullable String messageMask,@Nullable String messagePhonenumber);
//
//
//    List<MessageLog> findAllByMessageStatusAndMessagePhonenumber(@Nullable String messageStatus,@Nullable String messagePhonenumber);
//
//    List<MessageLog> findAllByUserAndMessageMaskAndMessageStatus(@Nullable String user,
//                                                                 @Nullable String messageMask,
//                                                                 @Nullable String messageStatus);
//
//    List<MessageLog> findAllByUserAndMessageStatusAndMessagePhonenumber(@Nullable String user,
//                                                                        @Nullable String messageStatus,
//                                                                        @Nullable String messagePhonenumber);
//
//    List<MessageLog> findAllByUserAndMessageMaskAndMessagePhonenumber(@Nullable String user,
//                                                                      @Nullable String messageMask,
//                                                                      @Nullable String messagePhonenumber);
//
//
//    List<MessageLog> findAllByMessageMaskAndMessageStatusAndMessagePhonenumber(@Nullable String messageMask,
//                                                                               @Nullable String messageStatus,
//                                                                               @Nullable String messagePhonenumber);




}

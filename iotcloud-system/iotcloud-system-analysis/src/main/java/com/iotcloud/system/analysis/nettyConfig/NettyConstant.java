package com.iotcloud.system.analysis.nettyConfig;

import com.kexsci.iotcloud.protocol.session.DeviceSession;
import io.netty.channel.ChannelFuture;

import java.util.concurrent.ConcurrentHashMap;

public class NettyConstant {

  public  static   ConcurrentHashMap<String, ChannelFuture> nettySerVermap = new ConcurrentHashMap<>();

  public  static   ConcurrentHashMap<String, DeviceSession> deviceSessionMap = new ConcurrentHashMap<>();

}

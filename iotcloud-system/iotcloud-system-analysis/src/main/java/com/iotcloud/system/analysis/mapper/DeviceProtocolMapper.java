package com.iotcloud.system.analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kexsci.iotcloud.protocol.protocol.DeviceProtocol;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceProtocolMapper extends BaseMapper<DeviceProtocol> {


    @Insert("INSERT INTO   iot_device_protocol  ( `port`, `name`, `file_path`, `decoder_class`, `unpack_class`) VALUES (#{port}, #{name}, #{filePath}, #{decoderClass}, #{unpackClass});")
    int insertDeviceProtocol(DeviceProtocol deviceProtocol);

    @Select(" select * from iot_device_protocol where port =#{port} and status <4")
    DeviceProtocol getDeviceProtocol(int port);

    @Select(" select * from iot_device_protocol where status <4")
    List<DeviceProtocol> getDeviceProtocolList();

}

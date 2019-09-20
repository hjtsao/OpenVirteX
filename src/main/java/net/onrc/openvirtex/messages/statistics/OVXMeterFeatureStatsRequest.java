package net.onrc.openvirtex.messages.statistics;

import net.onrc.openvirtex.elements.datapath.OVXSwitch;
import net.onrc.openvirtex.messages.OVXStatisticsReply;
import net.onrc.openvirtex.messages.OVXStatisticsRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectfloodlight.openflow.protocol.*;
import org.projectfloodlight.openflow.protocol.ver13.OFFactoryVer13;

import java.util.Collections;

public class OVXMeterFeatureStatsRequest extends OVXStatistics implements DevirtualizableStatistic {
    private Logger log = LogManager.getLogger(OVXMeterStatsRequest.class.getName());
    protected OFMeterFeaturesStatsRequest ofMeterFeaturesStatsRequest;
    public OVXMeterFeatureStatsRequest(OFMessage ofMessage){
        super(OFStatsType.METER_FEATURES);
        this.ofMeterFeaturesStatsRequest = (OFMeterFeaturesStatsRequest) ofMessage;
    }
    public void devirtualizeStatistic(OVXSwitch sw, OVXStatisticsRequest ovxStatisticsRequest) {
        OFMeterFeatures ofMeterFeatures = OFFactoryVer13.INSTANCE.buildMeterFeatures()
                .setBandTypes(0)
                .setMaxMeter(0)
                .setMaxColor((short)0)
                .setCapabilities(0)
                .setMaxBands((short)0)
                .build();
        OFMeterFeaturesStatsReply ofMeterFeaturesStatsReply = OFFactoryVer13.INSTANCE.buildMeterFeaturesStatsReply()
                .setFeatures(ofMeterFeatures)
                .setXid(ovxStatisticsRequest.getOFMessage().getXid())
                .build();
        OVXStatisticsReply ovxStatisticsReply = new OVXStatisticsReply(ofMeterFeaturesStatsReply);
        sw.sendMsg(ovxStatisticsReply, sw);
    }
}

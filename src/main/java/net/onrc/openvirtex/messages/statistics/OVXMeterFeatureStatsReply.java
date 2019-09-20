package net.onrc.openvirtex.messages.statistics;

import net.onrc.openvirtex.elements.datapath.PhysicalSwitch;
import net.onrc.openvirtex.messages.OVXStatisticsReply;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFMeterFeaturesStatsReply;
import org.projectfloodlight.openflow.protocol.OFStatsType;

public class OVXMeterFeatureStatsReply extends OVXStatistics implements VirtualizableStatistic {
    protected OFMeterFeaturesStatsReply ofMeterFeaturesStatsReply;
    public OVXMeterFeatureStatsReply(OFMessage ofMessage) {
        super(OFStatsType.METER_FEATURES);
        this.ofMeterFeaturesStatsReply = (OFMeterFeaturesStatsReply) ofMessage;
    }

    @Override
    public void virtualizeStatistic(PhysicalSwitch sw, OVXStatisticsReply msg) {

    }
}

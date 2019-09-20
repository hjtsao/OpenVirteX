package net.onrc.openvirtex.messages.statistics;

import net.onrc.openvirtex.elements.datapath.PhysicalSwitch;
import net.onrc.openvirtex.messages.OVXStatisticsReply;
import org.projectfloodlight.openflow.protocol.OFGroupFeaturesStatsReply;
import org.projectfloodlight.openflow.protocol.OFGroupStatsReply;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFStatsType;

public class OVXGroupStatsReply extends OVXStatistics implements VirtualizableStatistic {
    protected OFGroupStatsReply ofGroupStatsReply;
    public OVXGroupStatsReply(OFMessage ofMessage) {
        super(OFStatsType.GROUP);
        this.ofGroupStatsReply = (OFGroupStatsReply) ofMessage;
    }

    @Override
    public void virtualizeStatistic(PhysicalSwitch sw, OVXStatisticsReply msg) {

    }
}

package net.onrc.openvirtex.messages.statistics;

import net.onrc.openvirtex.elements.datapath.PhysicalSwitch;
import net.onrc.openvirtex.messages.OVXStatisticsReply;
import org.projectfloodlight.openflow.protocol.OFGroupDescStatsReply;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFStatsType;

public class OVXGroupDescStatsReply extends  OVXStatistics implements VirtualizableStatistic {
    protected OFGroupDescStatsReply ofGroupDescStatsReply;
    public OVXGroupDescStatsReply(OFMessage ofMessage) {
        super(OFStatsType.GROUP_DESC);
        this.ofGroupDescStatsReply = (OFGroupDescStatsReply) ofMessage;
    }

    @Override
    public void virtualizeStatistic(PhysicalSwitch sw, OVXStatisticsReply msg) {

    }
}

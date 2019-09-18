/*
 * ******************************************************************************
 *  Copyright 2019 Korea University & Open Networking Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ******************************************************************************
 *  Developed by Libera team, Operating Systems Lab of Korea University
 *  ******************************************************************************
 */
package net.onrc.openvirtex.api.service.handlers.monitoring;

import java.util.*;

import net.onrc.openvirtex.api.service.handlers.ApiHandler;
import net.onrc.openvirtex.api.service.handlers.HandlerUtils;
import net.onrc.openvirtex.api.service.handlers.MonitoringHandler;
import net.onrc.openvirtex.elements.Mappable;
import net.onrc.openvirtex.elements.OVXMap;
import net.onrc.openvirtex.elements.datapath.PhysicalSwitch;
import net.onrc.openvirtex.elements.network.OVXNetwork;
import net.onrc.openvirtex.elements.network.PhysicalNetwork;
import net.onrc.openvirtex.exceptions.InvalidDPIDException;
import net.onrc.openvirtex.exceptions.MissingRequiredField;


import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2ParamsType;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.onrc.openvirtex.messages.OVXFlowMod;
import org.projectfloodlight.openflow.protocol.OFFactories;
import org.projectfloodlight.openflow.protocol.OFFlowStatsEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetPhysicalFlowtable extends ApiHandler<Map<String, Object>> {

    private JSONRPC2Response resp = null;

    private static Logger log = LoggerFactory.getLogger("PhysicalFlowtable");

    @Override
    public JSONRPC2Response process(final Map<String, Object> params) {
        try {
            final Number dpid = HandlerUtils.<Number>fetchField(
                    MonitoringHandler.DPID, params, false, -1);
            final OVXMap map = OVXMap.getInstance();
            LinkedList<OFFlowStatsEntry> flows = new LinkedList<OFFlowStatsEntry>();

            if (dpid.longValue() == -1) {
                HashMap<String, List<Map<String, Object>>> res = new HashMap<String, List<Map<String, Object>>>();
                for (PhysicalSwitch sw : PhysicalNetwork.getInstance()
                        .getSwitches()) {
                    flows = aggregateFlowsBySwitch(sw.getSwitchId(), map);
                    res.put(sw.getSwitchName(), flowModsToMap(flows));
                }
                this.resp = new JSONRPC2Response(res, 0);
            } else {
                flows = aggregateFlowsBySwitch(dpid.longValue(), map);
                this.resp = new JSONRPC2Response(flowModsToMap(flows), 0);
            }

        } catch (ClassCastException | MissingRequiredField e) {
            this.resp = new JSONRPC2Response(new JSONRPC2Error(
                    JSONRPC2Error.INVALID_PARAMS.getCode(), this.cmdName()
                    + ": Unable to fetch virtual topology : "
                    + e.getMessage()), 0);
        } catch (final InvalidDPIDException e) {
            this.resp = new JSONRPC2Response(new JSONRPC2Error(
                    JSONRPC2Error.INVALID_PARAMS.getCode(), this.cmdName()
                    + ": Unable to fetch virtual topology : "
                    + e.getMessage()), 0);
        }

        return this.resp;

    }

    @Override
    public JSONRPC2ParamsType getType() {
        return JSONRPC2ParamsType.OBJECT;
    }

    private List<Map<String, String>> flowModsToMap(
            LinkedList<OFFlowStatsEntry> flows) {
        final List<Map<String, String>> res = new LinkedList<Map<String, String>>();
        for(OFFlowStatsEntry ofFlowStatsEntry : flows) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("Table ID: {}", ofFlowStatsEntry.getTableId().toString());
            map.put("Match: {}", ofFlowStatsEntry.getMatch().toString());
            map.put("Action: {}", ofFlowStatsEntry.getInstructions().toString());
            res.add(map);
        }
        /*for (OFFlowStatsEntry frep : flows) {
            OVXFlowMod fm = new OVXFlowMod(OFFactories.getFactory(frep.getVersion()).buildFlowModify()
                    .setActions(frep.getActions())
                    .setMatch(frep.getMatch())
                    .build()
            );

            res.add(fm.toMap());
        }*/
        return res;
    }

    private LinkedList<OFFlowStatsEntry> aggregateFlowsBySwitch(
            long dpid, Mappable map) {
        log.info("Get flow table for {}", dpid);
        LinkedList<OFFlowStatsEntry> flows = new LinkedList<OFFlowStatsEntry>();
        final PhysicalSwitch sw = PhysicalNetwork.getInstance().getSwitch(dpid);
        /////////////// Work in progress ///////////////
        for (Integer tid : map.listVirtualNetworks().keySet()) {
            log.info("tid: {}", tid);
            /*if (sw.getFlowStats(tid) != null) {
                flows.addAll(sw.getFlowStats(tid));
            }*/
        }
        if (sw.getFlowStats(0) != null) {
            flows.addAll(sw.getFlowStats(0));
        }



        /////////////// Work in progress ///////////////
        return flows;
    }

}

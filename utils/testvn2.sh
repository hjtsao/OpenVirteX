python ovxctl.py -n createNetwork tcp:140.113.194.252:6653 192.168.0.0 24
python ovxctl.py -n createSwitch 2 00:00:00:00:00:00:00:02
python ovxctl.py -n createSwitch 2 00:00:00:00:00:00:00:04
python ovxctl.py -n createPort 2 00:00:00:00:00:00:00:02 1
python ovxctl.py -n createPort 2 00:00:00:00:00:00:00:02 3
python ovxctl.py -n createPort 2 00:00:00:00:00:00:00:04 1
python ovxctl.py -n createPort 2 00:00:00:00:00:00:00:04 2
python ovxctl.py -n connectLink 2 00:a4:23:05:00:00:00:01 2 00:a4:23:05:00:00:00:02 2 spf 1
python ovxctl.py -n connectHost 2 00:a4:23:05:00:00:00:01 1 00:00:00:00:00:21
python ovxctl.py -n connectHost 2 00:a4:23:05:00:00:00:02 1 00:00:00:00:00:41


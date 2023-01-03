package aspects;

import larva.*;
public aspect _asp_alertLimit0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_alertLimit0.initialize();
}
}
before () : (call(* *.postAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alertLimit0.lock){

_cls_alertLimit0 _cls_inst = _cls_alertLimit0._get_cls_alertLimit0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 74/*counting*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 74/*counting*/);
}
}
before ( int alertsReturned) : (call(* *.alertLimitCheck(..)) && args(alertsReturned) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alertLimit0.lock){

_cls_alertLimit0 _cls_inst = _cls_alertLimit0._get_cls_alertLimit0_inst();
_cls_inst.alertsReturned = alertsReturned;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 76/*returned*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 76/*returned*/);
}
}
before () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alertLimit0.lock){

_cls_alertLimit0 _cls_inst = _cls_alertLimit0._get_cls_alertLimit0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 78/*deleted*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 78/*deleted*/);
}
}
}
package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_alertLimit0 implements _callable{

public static PrintWriter pw; 
public static _cls_alertLimit0 root;

public static LinkedHashMap<_cls_alertLimit0,_cls_alertLimit0> _cls_alertLimit0_instances = new LinkedHashMap<_cls_alertLimit0,_cls_alertLimit0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\borgk\\workspace\\Assignment2/src/output_alertLimit.txt");

root = new _cls_alertLimit0();
_cls_alertLimit0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_alertLimit0 parent; //to remain null - this class does not have a parent!
public static int alertsReturned;
int no_automata = 1;
 public int alertCount =0 ;
 public boolean validCredentials =true ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_alertLimit0() {
}

public void initialisation() {
}

public static _cls_alertLimit0 _get_cls_alertLimit0_inst() { synchronized(_cls_alertLimit0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_alertLimit0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_alertLimit0_instances){
_performLogic_badLoginsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_alertLimit0[] a = new _cls_alertLimit0[1];
synchronized(_cls_alertLimit0_instances){
a = _cls_alertLimit0_instances.keySet().toArray(a);}
for (_cls_alertLimit0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_alertLimit0_instances){
_cls_alertLimit0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_badLoginsProperty = 37;

public void _performLogic_badLoginsProperty(String _info, int... _event) {

_cls_alertLimit0.pw.println("[badLoginsProperty]AUTOMATON::> badLoginsProperty("+") STATE::>"+ _string_badLoginsProperty(_state_id_badLoginsProperty, 0));
_cls_alertLimit0.pw.flush();

if (0==1){}
else if (_state_id_badLoginsProperty==37){
		if (1==0){}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==true )){
		alertCount ++;
_cls_alertLimit0.pw .println ("Alert Created. Current number of alerts: "+alertCount );

		_state_id_badLoginsProperty = 34;//moving to state belowLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,78/*deleted*/)) && (validCredentials ==true )){
		alertCount =0 ;
_cls_alertLimit0.pw .println ("Alerts Deleted!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,78/*deleted*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,76/*returned*/)) && (validCredentials ==true &&alertsReturned ==0 )){
		_cls_alertLimit0.pw .println ("No Alerts Found!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,76/*returned*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
}
else if (_state_id_badLoginsProperty==34){
		if (1==0){}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==true &&alertCount <4 )){
		alertCount ++;
_cls_alertLimit0.pw .println ("Alert Created. Current number of alerts: "+alertCount );

		_state_id_badLoginsProperty = 34;//moving to state belowLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 34;//moving to state belowLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==true &&alertCount >4 )){
		alertCount ++;
_cls_alertLimit0.pw .println ("Alert Created. Current number of alerts: "+alertCount );

		_state_id_badLoginsProperty = 36;//moving to state aboveLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,78/*deleted*/)) && (validCredentials ==true )){
		alertCount =0 ;
_cls_alertLimit0.pw .println ("Alerts Deleted!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,78/*deleted*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 34;//moving to state belowLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,76/*returned*/)) && (validCredentials ==true &&alertsReturned <6 )){
		_cls_alertLimit0.pw .println ("Returning Alerts.");

		_state_id_badLoginsProperty = 34;//moving to state belowLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,76/*returned*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 34;//moving to state belowLimit
		_goto_badLoginsProperty(_info);
		}
}
else if (_state_id_badLoginsProperty==36){
		if (1==0){}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==true &&alertCount >4 )){
		alertCount ++;
_cls_alertLimit0.pw .println ("Alert Created. Current number of alerts: "+alertCount );

		_state_id_badLoginsProperty = 36;//moving to state aboveLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,74/*counting*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 36;//moving to state aboveLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,78/*deleted*/)) && (validCredentials ==true )){
		alertCount =0 ;
_cls_alertLimit0.pw .println ("Alerts Deleted!");

		_state_id_badLoginsProperty = 37;//moving to state noAlerts
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,78/*deleted*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 36;//moving to state aboveLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,76/*returned*/)) && (validCredentials ==false )){
		_cls_alertLimit0.pw .println ("Invalid Credentials!");

		_state_id_badLoginsProperty = 36;//moving to state aboveLimit
		_goto_badLoginsProperty(_info);
		}
		else if ((_occurredEvent(_event,76/*returned*/)) && (validCredentials ==true &&alertsReturned >5 )){
		_cls_alertLimit0.pw .println ("Returning Alerts.");

		_state_id_badLoginsProperty = 35;//moving to state aboveLimitReturned
		_goto_badLoginsProperty(_info);
		}
}
}

public void _goto_badLoginsProperty(String _info){
_cls_alertLimit0.pw.println("[badLoginsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_badLoginsProperty(_state_id_badLoginsProperty, 1));
_cls_alertLimit0.pw.flush();
}

public String _string_badLoginsProperty(int _state_id, int _mode){
switch(_state_id){
case 37: if (_mode == 0) return "noAlerts"; else return "noAlerts";
case 34: if (_mode == 0) return "belowLimit"; else return "(((SYSTEM REACHED AN ACCEPTED STATE)))  belowLimit";
case 35: if (_mode == 0) return "aboveLimitReturned"; else return "!!!SYSTEM REACHED BAD STATE!!! aboveLimitReturned "+new _BadStateExceptionalertLimit().toString()+" ";
case 36: if (_mode == 0) return "aboveLimit"; else return "aboveLimit";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}
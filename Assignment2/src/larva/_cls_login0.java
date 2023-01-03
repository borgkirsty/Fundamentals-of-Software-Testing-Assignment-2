package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_login0 implements _callable{

public static PrintWriter pw; 
public static _cls_login0 root;

public static LinkedHashMap<_cls_login0,_cls_login0> _cls_login0_instances = new LinkedHashMap<_cls_login0,_cls_login0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\borgk\\workspace\\Assignment2/src/output_login.txt");

root = new _cls_login0();
_cls_login0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_login0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_login0() {
}

public void initialisation() {
}

public static _cls_login0 _get_cls_login0_inst() { synchronized(_cls_login0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_login0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_login0_instances){
_performLogic_LoginProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_login0[] a = new _cls_login0[1];
synchronized(_cls_login0_instances){
a = _cls_login0_instances.keySet().toArray(a);}
for (_cls_login0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_login0_instances){
_cls_login0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_LoginProperty = 25;

public void _performLogic_LoginProperty(String _info, int... _event) {

_cls_login0.pw.println("[LoginProperty]AUTOMATON::> LoginProperty("+") STATE::>"+ _string_LoginProperty(_state_id_LoginProperty, 0));
_cls_login0.pw.flush();

if (0==1){}
else if (_state_id_LoginProperty==24){
		if (1==0){}
		else if ((_occurredEvent(_event,60/*loggingOut*/))){
		_cls_login0.pw .println ("Log Out observed");

		_state_id_LoginProperty = 25;//moving to state loggedOut
		_goto_LoginProperty(_info);
		}
}
else if (_state_id_LoginProperty==25){
		if (1==0){}
		else if ((_occurredEvent(_event,56/*invalidLogin*/))){
		_cls_login0.pw .println ("Invalid Login observed.");

		_state_id_LoginProperty = 25;//moving to state loggedOut
		_goto_LoginProperty(_info);
		}
		else if ((_occurredEvent(_event,58/*validLogin*/))){
		_cls_login0.pw .println ("Valid Login observed");

		_state_id_LoginProperty = 24;//moving to state loggedIn
		_goto_LoginProperty(_info);
		}
}
}

public void _goto_LoginProperty(String _info){
_cls_login0.pw.println("[LoginProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_LoginProperty(_state_id_LoginProperty, 1));
_cls_login0.pw.flush();
}

public String _string_LoginProperty(int _state_id, int _mode){
switch(_state_id){
case 24: if (_mode == 0) return "loggedIn"; else return "(((SYSTEM REACHED AN ACCEPTED STATE)))  loggedIn";
case 25: if (_mode == 0) return "loggedOut"; else return "loggedOut";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}
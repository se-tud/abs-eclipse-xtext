package abs.frontend.typechecker.locationtypes.infer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abs.frontend.typechecker.locationtypes.LocationType;

public class Environment {
    
    Map<TypedVar,Integer> map = new HashMap<TypedVar,Integer>();
    List<TypedVar> list = new ArrayList<TypedVar>();
    int current = 0;
    
    public int get(LocationTypeVariable v, LocationType t) {
        assert v != null;
        TypedVar tv = new TypedVar(v, t);
        if (map.containsKey(tv)) {
            return map.get(tv);
        } else {
            current++;
            map.put(tv, current);
            list.add(current - 1, tv);
            return current;
        }
    }
    
    List<TypedVar> vars() {
        return list;
    }

}

class TypedVar {
    
    LocationTypeVariable v;
    LocationType t;
    public TypedVar(LocationTypeVariable v, LocationType t) {
        super();
        this.v = v;
        this.t = t;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((t == null) ? 0 : t.hashCode());
        result = prime * result + ((v == null) ? 0 : v.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TypedVar other = (TypedVar) obj;
        if (t == null) {
            if (other.t != null)
                return false;
        } else if (!t.equals(other.t))
            return false;
        if (v == null) {
            if (other.v != null)
                return false;
        } else if (!v.equals(other.v))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return v + " : " + t;
    }
    
    
}

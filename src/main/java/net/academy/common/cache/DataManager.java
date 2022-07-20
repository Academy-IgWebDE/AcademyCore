package net.academy.common.cache;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import net.academy.common.utils.Data;

import java.util.HashMap;

/**
 * @author JonasDev
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.FINISHED)
public class DataManager {

    private HashMap<Object, Object> cacheMap;
    private final String id;

    /**
     * get the Data-manager from the identifier.
     * works mostly like a hashMap
     *
     * @param identifier is the name of the DataManagers, to get to data from other classes
     */
    public DataManager(String identifier) {
        this.id = identifier;
        cacheMap = new HashMap<>();
        if (!Data.globalCache.containsKey(identifier)) {
            Data.globalCache.put(identifier, new HashMap<>());
        }
        cacheMap = Data.globalCache.get(identifier);
    }

    /**
     * returns the value of the key
     *
     * @param identified set the key, to get the value
     * @return the value from the key
     */
    public Object get(Object identified) {
        return cacheMap.getOrDefault(identified, null);
    }

    /**
     * puts to the data save a key with a value
     *
     * @param identifier the key, to get the value
     * @param value      the value, that you can get by the key
     */

    public void put(Object identifier, Object value) {
        cacheMap.put(identifier, value);
        update();
    }

    /**
     * removes the value by the key
     *
     * @param identifier the key to remove the value
     */
    public void remove(Object identifier) {
        cacheMap.remove(identifier);
        update();
    }

    /**
     * looks in the data save, if a key is set or not
     *
     * @param identifier the key that be tested
     * @return the statement whether the key is set
     */
    public boolean contains(Object identifier) {
        return cacheMap.containsKey(identifier);
    }

    /**
     * get all keys from the data save
     *
     * @return the keys from the data save as object array
     */
    public Object[] keys() {
        return cacheMap.keySet().toArray();
    }

    /**
     * get all values from the data save
     *
     * @return the values from the data save as object array
     */
    public Object[] values() {
        return cacheMap.values().toArray();
    }

    private void update() {
        Data.globalCache.replace(id, cacheMap);
    }


}

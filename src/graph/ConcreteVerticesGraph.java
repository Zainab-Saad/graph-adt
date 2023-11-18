/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(String vertex) 
    {
        for (Vertex v:vertices)
        {
            // if vertex already exists
            if (v.label.equals(vertex))
                return false;
        }

        // if vertex does't already exist
        Vertex v = new Vertex(vertex);
        vertices.add(v);
        return true;
    }
    
    @Override public int set(String source, String target, int weight) 
    {
        int temp = 0;

        // checking if that edge exists;
        for (Vertex vertex:vertices)
        {
            // checking if the source exists
            if (vertex.label.equals(source))
            {
                // checking if the source has a path to the target
                if (vertex.destinations().contains(target))
                {
                    temp = vertex.paths.get(target);
                    if (weight != 0)
                        vertex.paths.replace(target, weight); 
                    else
                        vertex.paths.remove(target);
                    
                    return temp;
                }
                // otherwise creating an edge to that target
                else
                    vertex.paths.put(target, weight);
            }
        }

        return temp;
    }
    
    @Override public boolean remove(String vertex) 
    {
        boolean sources_deleted = false;
        boolean targets_deleted = false;
        for (Vertex v:vertices)
        {
            // if the vertex exists
            if (v.label.equals(vertex))
            {
                // clearing all target paths
                v.paths.clear();
                targets_deleted = true;
            }
            
            // or if the vertex is the destination of some path leading up from a vertex
            else if (v.paths.keySet().contains(vertex))
                v.paths.remove(vertex);
        }
        if (targets_deleted && sources_deleted)
            return true;
        return false;
    }
    
    @Override public Set<String> vertices() 
    {
        Set<String> v = new HashSet<String>();
        for (Vertex vertex:vertices)
            v.add(vertex.label);
        
        return v;
    }
    
    @Override public Map<String, Integer> sources(String target) 
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Vertex vertex:vertices)
        {
            // get all the destinations from the vertex
            Set<String> all_targets = vertex.paths.keySet();
            for(String t:all_targets)
            {
                // checking if the target matches the specified target
                if (t.equals(target))
                    map.put(vertex.label, vertex.paths.get(target));
            }
        }
        return map;
    }
    
    @Override public Map<String, Integer> targets(String source) 
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Vertex vertex:vertices)
        {
            // if the vertex is the source vertex
            if (vertex.label.equals(source))
                map.putAll(vertex.paths);
        }
        return map;
    }
    
    // TODO toString()
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // TODO fields
    String label;
    // hashMap containing each destination the vertex leads to with its weight as value
    Map<String, Integer> paths = new HashMap<String, Integer>();
    List<String> destinations = new ArrayList<String>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    Vertex(String label)
    {
        this.label = label;
    }

    // constructor overload
    Vertex(String label, Map<String, Integer> map)
    {
        this.label = label;
        this.paths = map;
    }
    
    // TODO checkRep
    
    // TODO methods
    // add a new path
    public void addPath(String target, int weight)
    {
        paths.put(target, weight);
    }

    // get list of all destinations from that vertex
    public Set<String> destinations()
    {
        return paths.keySet();
    }
    
    // TODO toString()
    
}

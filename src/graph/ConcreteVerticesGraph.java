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
public class ConcreteVerticesGraph implements Graph<String> 
{
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    public boolean add(String vertex) 
    {
        for (Vertex v:vertices)
        {
            // if vertex already exists
            if (v.label.toLowerCase().equals(vertex.toLowerCase()))
                return false;
        }

        // if vertex does't already exist
        Vertex v = new Vertex(vertex);
        vertices.add(v);
        return true;
    }
    
    public int set(String source, String target, int weight)  
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
    
    public boolean remove(String vertex) 
    {
        boolean sources_deleted = false;
        boolean targets_deleted = false;
        
//      copy the elements into another list
        List<Vertex> tempVertices = new ArrayList<Vertex>();
        for (Vertex v : vertices) {
        	tempVertices.add(v);
        }
        
        for (Vertex v: tempVertices)
        {
            // if the vertex exists
            if (v.label.equals(vertex))
            {
                // clearing all target paths
                v.paths.clear();
                vertices.remove(v);
                targets_deleted = true;
            }
            
            // or if the vertex is the destination of some path leading up from a vertex
            else if (v.paths.keySet().contains(vertex)) {
            	v.paths.remove(vertex);
            	vertices.remove(v);
            	sources_deleted = true;
            }
        }
        if (targets_deleted || sources_deleted)
            return true;
        return false;
    }
    
    // to set an edge once with number of occurrences as weight
    public int setOnce(String source, String target)
    {
        int count = 1;
        Vertex vertex = null;
        // finding the source vertex
        for (Vertex v:vertices)
        {
            if (v.label.toLowerCase().equals(source.toLowerCase()))
                vertex = v;
        }

        // if the path already exists
        Set<String> Paths = vertex.paths.keySet();
        for (String Path:Paths)
        {
            // if path to the target already exists
            if (vertex.paths.get(Path).toString().toLowerCase().equals(target.toLowerCase()))
            // increase the weight
                vertex.paths.replace(target, vertex.paths.get(target)+1);
            return vertex.paths.get(target);
        }
        
        // if path doesn't already exist, create the path with weight 1
        vertex.paths.put(target, count);
        // also add the target in the destinations (list) of the source
        vertex.destinations.add(target);

        return count;
    }

    public Set<String> vertices() 
    {
        Set<String> v = new HashSet<String>();
        for (Vertex vertex:vertices)
            v.add(vertex.label);
        
        return v;
    }
    
    public Map<String, Integer> sources(String target) 
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
    
    public Map<String, Integer> targets(String source) 
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
    public void printGraph()
    {
        for (Vertex vertex:vertices)
        {
            for (String destination:vertex.destinations)
                System.out.println(vertex + " -> "+ destination);
            System.out.println();
        }

        return;
    }
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
    // hashMap containing each destination the vertex leads to with its weight
    Map<String, Integer> paths = new HashMap<String, Integer>();
    List<String> destinations = new ArrayList<String>();
    
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

    // add a new path if one doesnt already exist
    public int addPath(String target)
    {
        int count = 1;
        // if the path already exists
        if (paths.keySet().contains(target))
        {
            // increase the weight
            paths.replace(target, paths.get(target)+1);
            return paths.get(target);
        }
        // if path doesn't already exist, create the path with weight 1
        paths.put(target, count);
        return count;
    }


    // get list of all destinations from that vertex
    public Set<String> destinations()
    {
        return paths.keySet();
    }
    
    // TODO toString()
    
}

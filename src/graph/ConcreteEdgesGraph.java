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
public class ConcreteEdgesGraph implements Graph<String> 
{
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
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
        if (vertices.contains(vertex))
            return false;
        
        vertices.add(vertex);
        return true;
    }
    
    @Override public int set(String source, String target, int weight) 
    {
        int temp;
        for (Edge edge:edges)
        {
            // if edge exists
            if (edge.source.equals(source) && edge.destination.equals(target))
            {
                temp = edge.weight;
                // updating the weight of the already existing edge
                edge.weight = weight;
                return temp;
            }
        }
        // if edge does not exist, create one
        Edge edge = new Edge(source, target, weight);
        edges.add(edge);
        return 0;
    }
    
    @Override public boolean remove(String vertex) 
    {
        return vertices.remove(vertex);
    }
    
    @Override public Set<String> vertices() 
    {
        return vertices;
    }
    
    @Override public Map<String, Integer> sources(String target) 
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Edge edge:edges)
        {
            // if target of that edge matches the specified target
            if (edge.destination.equals(target))
                map.put(edge.source, edge.weight);
        }
        return map;
    }
    
    @Override public Map<String, Integer> targets(String source) 
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Edge edge:edges)
        {
            // if target of that edge matches the specified target
            if (edge.source.equals(source))
                map.put(edge.destination, edge.weight);
        }
        return map;
    }
    
    // TODO toString() 
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge 
{
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    String source, destination;
    int weight;
    
    /**
     * Creates an edge
     * @param source label of source vertex
     * @param destination label of destination vertex
     * @param weight weight of the edge
    */
    Edge(String source, String destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    // TODO checkRep
    
    // TODO methods
    
    // TODO toString()
    
}

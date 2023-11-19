/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing Strategy for instance method add(vertex)
     * 
     * vertex does not exist - should be added in the graph and return true
     * vertex already exists - should not be added and should return false
     * also tests instance method vertices()
     */
    @Test
    public void testAddVertexNotExists() {
    	Graph<String> eGraph = emptyInstance();
    	String vertex = "Label";
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(vertex);
    	int initialSize = eGraph.vertices().size();
    	boolean added = eGraph.add(vertex);
    	int sizeAfterAdd = eGraph.vertices().size();
    	Set<String> vertices = eGraph.vertices();
    	
    	assertEquals("Expected vertices() size to be 0", 0, initialSize);
    	assertTrue("Expected vertex to be added - return true", added);
    	assertEquals("Expected vertices() size to be increased by 1", initialSize+1, sizeAfterAdd);
    	assertEquals("Expected vertices() to contain the vertex label", expectedVertices, vertices);
    }
    
    @Test
    public void testAddVertexAlreadyExists() {
    	Graph<String> eGraph = emptyInstance();
    	String vertex = "Label";
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(vertex);
    	boolean added = eGraph.add(vertex);
    	int initialSize = eGraph.vertices().size();
    	added = eGraph.add(vertex);
    	int sizeAfterAdd = eGraph.vertices().size();
    	Set<String> vertices = eGraph.vertices();
    	
    	assertEquals("Expected vertices() size to be 1", 1, initialSize);
    	assertFalse("Expected vertex to not be added - return false", added);
    	assertEquals("Expected vertices() size to remain same i.e; 1", initialSize, sizeAfterAdd);
    	assertEquals("Expected vertices() to contain only one vertex label", expectedVertices, vertices);	
    }
    
    /*
     * Testing Strategy for set(source, target, weight);
     * 
     * when edge does not exist - creates edge and returns 0
     * when the source and target exist
     * when the weight is set to 0
     * when the weight is set to negative number
     * 
     * 
     * also tests instance methods sources(), targets() and sources()
     */
    
//    when the source vertex does not exist - should not create an edge
    @Test
    public void testSetEdgeDoesntExist() {
    	Graph<String> eGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	int weight = 10;
    	
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	expectedTargets.put(target, weight);
    	
    	int temp = eGraph.set(src, target, weight);
    	Map<String, Integer> actualTargets = eGraph.targets(src);
    	
    	assertEquals("Expected temp to be zero as edge does not exist", 0, temp);
    	assertEquals("Expected targets map to contain target, weight", expectedTargets, actualTargets);
    }
    
//    when the target vertex does not exist - creates an edge
    @Test
    public void testSetEdgeAlreadyExists() {
    	Graph<String> eGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	int weight = 10;
    	
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	expectedTargets.put(target, weight);
    	
    	int temp = eGraph.set(src, target, weight);
    	temp = eGraph.set(src, target, weight);
    	Map<String, Integer> actualTargets = eGraph.targets(src);
    	
    	assertEquals("Expected temp to be zero as edge does not exist", weight, temp);
    	assertEquals("Expected targets map to contain target, weight", expectedTargets, actualTargets);
    }
    
    
    /*
     * Testing Strategy for instance method remove(vertex)
     * vertex does not exist in the graph
     * src vertex is given - all target vertices should be removed
     * target vertex is given - all src vertices should be given
     * 
     * also tests vertices(), sources(), targets()
     */
//    vertex does not exist in the graph
    @Test
    public void testRemoveVertexDoesNotExist () {
    	Graph<String> eGraph = emptyInstance();
    	String src = "src";
    	Set<String> expectedVertices = new HashSet<String>();
    	Set<String> actualVertices = eGraph.vertices();
    	boolean removed = eGraph.remove(src);    
    	
    	assertFalse("Expected to return false - as vertex does not exist in graph", removed);
    	assertEquals("Expected vertices to be empty", expectedVertices, actualVertices);
    }
//    src vertex is given - all target vertices should be removed
    @Test
    public void testRemoveAllTargetsRemoved () {
    	Graph<String> eGraph = emptyInstance();
    	String src = "src";
    	String target1 = "target1";
    	String target2 = "target2";
    	String target3 = "target3";
    	Set <String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(target3);
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	
    	eGraph.add(src);
    	eGraph.add(target3);
    	
    	eGraph.set(src, target1, 10);
    	eGraph.set(src, target2, 20);
    	
    	boolean removed = eGraph.remove(src);
    	
    	Set <String> actualVertices = eGraph.vertices();
    	Map<String, Integer> actualTargets = eGraph.targets(src);
    	
    	assertTrue("Expected vertex to be removed", removed);
    	assertEquals("Expected all target vertices to be removed, non target vertices should be present in the list", expectedVertices, actualVertices);
    	assertEquals("Expected all targets to be removed", expectedTargets, actualTargets);
    }
    
//    target vertex is given - all src vertices should be given
    @Test
    public void testRemoveAllSrcRemoved () {
    	Graph<String> eGraph = emptyInstance();
    	String src1 = "src1";
    	String src2 = "src2";
    	String src3 = "src3";
    	String target = "target";
    	Set <String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(src3);
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	
    	eGraph.add(src1);
    	eGraph.add(src2);
    	eGraph.add(src3);
    	
    	eGraph.set(src1, target, 10);
    	eGraph.set(src2, target, 20);
    	
    	boolean removed = eGraph.remove(target);
    	
    	Set <String> actualVertices = eGraph.vertices();
    	Map<String, Integer> actualTargets = eGraph.sources(target);
    	
    	
    	assertTrue("Expected vertex to be removed", removed);
    	assertEquals("Expected all targets to be removed", expectedTargets, actualTargets);
    	assertEquals("Expected all target vertices to be removed, non target vertices should be present in the list", expectedVertices, actualVertices);
    }
}

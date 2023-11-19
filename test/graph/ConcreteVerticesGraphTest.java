/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing Strategy for instance method add()
     * Test with graph where the
     * 		given vertex does not already exist
     * 	    given vertex label already exists
     * 
     * This also tests instance method vertices()
     */
    @Test
    public void testAddVertexLabelNotExists() {
    	Graph<String> vGraph = emptyInstance();
    	String vertexLabel = "Label";
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(vertexLabel);
    	int initialSize = vGraph.vertices().size();
    	boolean added = vGraph.add(vertexLabel);
    	int sizeAfterAdd = vGraph.vertices().size();
    	Set<String> vertices = vGraph.vertices();
    	
    	assertEquals("Expected vertices() size to be 0", 0, initialSize);
    	assertTrue("Expected vertex to be added - return true", added);
    	assertEquals("Expected vertices() size to be increased by 1", initialSize+1, sizeAfterAdd);
    	assertEquals("Expected vertices() to contain the vertex label", expectedVertices, vertices);
    }
    
    @Test
    public void testAddVertexLabelAlreadyExists() {
    	Graph<String> vGraph = emptyInstance();
    	String vertexLabel = "Label";
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(vertexLabel);
    	boolean added = vGraph.add(vertexLabel);
    	int initialSize = vGraph.vertices().size();
    	added = vGraph.add(vertexLabel);
    	int sizeAfterAdd = vGraph.vertices().size();
    	Set<String> vertices = vGraph.vertices();
    	
    	assertEquals("Expected vertices() size to be 1", 1, initialSize);
    	assertFalse("Expected vertex to not be added - return false", added);
    	assertEquals("Expected vertices() size to remain same i.e; 1", initialSize, sizeAfterAdd);
    	assertEquals("Expected vertices() to contain only one vertex label", expectedVertices, vertices);	
    }
    
    /*
     * Testing Strategy for set(source, target, weight);
     * 
     * when the source vertex does not exist - should not create an edge
     * when the target vertex does not exist - creates an edge
     * when the source and target exist
     * when the weight is set to 0
     * when the weight is set to negative number
     * 
     * 
     * also tests instance methods sources(), targets() and sources()
     */
    
//    when the source vertex does not exist - should not create an edge
    @Test
    public void testSetSrcDoesNotExist() {
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	int weight = 10;
    	int temp = vGraph.set(src, target, weight);
    	Map<String, Integer> actualTargets = vGraph.targets(src);
    	Set<String> vertices = new HashSet<String>();
    	
    	assertEquals("Expected temp to be zero as src vertex does not exist", 0, temp);
    	assertEquals("Expected vertices() to be empty", vGraph.vertices(), vertices);
    	assertEquals("Expected targets map for source to be empty", expectedTargets, actualTargets);
    }
    
//    when the target vertex does not exist - creates an edge
    @Test
    public void testSetTargetDoesNotExist() {
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	int weight = 10;
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	expectedTargets.put(target, weight);
    	
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(src);
    	expectedVertices.add(target);
    	
//    	add the src vertex only, not the target vertex
    	vGraph.add(src);
    	
    	int temp = vGraph.set(src, target, weight);
    	Set<String> actualVertices = vGraph.vertices();
    	Map<String, Integer> actualTargets = vGraph.targets(src);
    	
    	
    	assertEquals("Expected temp to be equal to 0 as edge between src and target is just created", 0, temp);
    	assertEquals("Expected vertices() to contain both src and target", vGraph.vertices(), actualVertices);
    	assertEquals("Expected targets map for source to have <target, weight>", expectedTargets, actualTargets);
    }
    
//   when the source and target exist
    @Test
    public void testSetEdgeAlreadyExists() {
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	int prevWeight = 2;
    	int weight = 10;
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	expectedTargets.put(target, weight);
    	
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(src);
    	expectedVertices.add(target);
    	
//    	add the src vertex only, not the target vertex
    	vGraph.add(src);
    	
    	vGraph.set(src, target, 2);
    	
    	int temp = vGraph.set(src, target, weight);
    	Set<String> actualVertices = vGraph.vertices();
    	Map<String, Integer> actualTargets = vGraph.targets(src);
    	
    	
    	assertEquals("Expected temp to be equal to previous weight as edge between src and target is just created", prevWeight, temp);
    	assertEquals("Expected vertices() to contain both src and target", vGraph.vertices(), actualVertices);
    	assertEquals("Expected targets map for source to have <target, weight>", expectedTargets, actualTargets);
    }
    
//    when the weight is set to 0
    @Test
    public void testSetWeightIsZero() {
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	int prevWeight = 2;
    	int weight = 0;
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(src);
    	expectedVertices.add(target);
    	
//    	add the src vertex only, not the target vertex
    	vGraph.add(src);
    	
    	vGraph.set(src, target, 2);
    	
    	int temp = vGraph.set(src, target, weight);
    	Set<String> actualVertices = vGraph.vertices();
    	Map<String, Integer> actualTargets = vGraph.targets(src);
    	
    	
    	assertEquals("Expected temp to be equal to previous weight as edge between src and target is just created", prevWeight, temp);
    	assertEquals("Expected vertices() to contain both src and target", vGraph.vertices(), actualVertices);
    	assertEquals("Expected targets map for source to have <target, weight>", expectedTargets, actualTargets);
    }
    
//  when the weight is set to negative number   
    @Test
    public void testSetWeightIsNegative () {
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	String target = "target";
    	int weight = -10;
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	expectedTargets.put(target, weight);
    	
    	Set<String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(src);
    	expectedVertices.add(target);
    	
//    	add the src vertex only, not the target vertex
    	vGraph.add(src);
    	
    	int temp = vGraph.set(src, target, weight);
    	Set<String> actualVertices = vGraph.vertices();
    	Map<String, Integer> actualTargets = vGraph.targets(src);
    	
    	
    	assertEquals("Expected temp to be equal to 0 as edge between src and target is just created", 0, temp);
    	assertEquals("Expected vertices() to contain both src and target", vGraph.vertices(), actualVertices);
    	assertEquals("Expected targets map for source to have <target, weight>", expectedTargets, actualTargets);
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
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	Set<String> expectedVertices = new HashSet<String>();
    	Set<String> actualVertices = vGraph.vertices();
    	boolean removed = vGraph.remove(src);    
    	
    	assertFalse("Expected to return false - as vertex does not exist in graph", removed);
    	assertEquals("Expected vertices to be empty", expectedVertices, actualVertices);
    }
//    src vertex is given - all target vertices should be removed
    @Test
    public void testRemoveAllTargetsRemoved () {
    	Graph<String> vGraph = emptyInstance();
    	String src = "src";
    	String target1 = "target1";
    	String target2 = "target2";
    	String target3 = "target3";
    	Set <String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(target3);
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	
    	vGraph.add(src);
    	vGraph.add(target3);
    	
    	vGraph.set(src, target1, 10);
    	vGraph.set(src, target2, 20);
    	
    	boolean removed = vGraph.remove(src);
    	
    	Set <String> actualVertices = vGraph.vertices();
    	Map<String, Integer> actualTargets = vGraph.targets(src);
    	
    	assertTrue("Expected vertex to be removed", removed);
    	assertEquals("Expected all target vertices to be removed, non target vertices should be present in the list", expectedVertices, actualVertices);
    	assertEquals("Expected all targets to be removed", expectedTargets, actualTargets);
    }
    
//    target vertex is given - all src vertices should be given
    @Test
    public void testRemoveAllSrcRemoved () {
    	Graph<String> vGraph = emptyInstance();
    	String src1 = "src1";
    	String src2 = "src2";
    	String src3 = "src3";
    	String target = "target";
    	Set <String> expectedVertices = new HashSet<String>();
    	expectedVertices.add(src3);
    	Map<String, Integer> expectedTargets = new HashMap<String, Integer>();
    	
    	vGraph.add(src1);
    	vGraph.add(src2);
    	vGraph.add(src3);
    	
    	vGraph.set(src1, target, 10);
    	vGraph.set(src2, target, 20);
    	
    	boolean removed = vGraph.remove(target);
    	
    	Set <String> actualVertices = vGraph.vertices();
    	Map<String, Integer> actualTargets = vGraph.sources(target);
    	
    	
    	assertTrue("Expected vertex to be removed", removed);
    	assertEquals("Expected all targets to be removed", expectedTargets, actualTargets);
    	assertEquals("Expected all target vertices to be removed, non target vertices should be present in the list", expectedVertices, actualVertices);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa_ga_final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static psa_ga_final_project.Utility.calcDistance;


/**
 *
 * @author darsh
 */
public class LineSegmentGATest {
    private ArrayList<Segment> segmentList;

    @Test
    public void segmentsAdjancencyTest() {
        // These tests should pass if two segments are close to each other within distance of 1.5
        
        Utility u = new Utility();
        
        // These segments (dashes) are adjacent, one of the points have distance less than 1.5
        Segment s1 = new Segment(new Point(1, 2), new Point(3, 2));
        Segment s2 = new Segment(new Point(4, 2), new Point(5, 2));
        assertEquals(true, u.areSegmentsAdjacent(s1, s2));
        
        // These segments (dashes) are adjacent, one of the points have distance less than 1.5
        Segment s3 = new Segment(new Point(3, 3), new Point(5, 5));
        Segment s4 = new Segment(new Point(6, 6), new Point(7, 7));
        assertEquals(true, u.areSegmentsAdjacent(s3, s4));
        
        // These segments are not adjacent enough to be called as dashed segments 
        Segment s5 = new Segment(new Point(1, 1), new Point(3, 2));
        Segment s6 = new Segment(new Point(4, 2), new Point(6, 3));
        assertEquals(true, u.areSegmentsAdjacent(s5, s6));
        
        // These segments are not adjacent enough to be called as dashed segments 
        Segment s7 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s8 = new Segment(new Point(5, 8), new Point(7, 5));
        assertEquals(false, u.areSegmentsAdjacent(s7, s8));
        
        // These segments are not adjacent enough to be called as dashed segments 
        Segment s9 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s10 = new Segment(new Point(100, 101), new Point(150, 4));
        assertEquals(false, u.areSegmentsAdjacent(s9, s10));
    }
    
    @Test
    public void segmentsCollinearityTest() {
        Utility u = new Utility();
        
        // These segments are exactly collinear
        Segment s1 = new Segment(new Point(1, 2), new Point(3, 2));
        Segment s2 = new Segment(new Point(5, 2), new Point(7, 2));
        assertEquals(true, u.areSegmentsColinear(s1, s2));
        
        // These segments are exactly collinear
        Segment s3 = new Segment(new Point(3, 3), new Point(5, 5));
        Segment s4 = new Segment(new Point(7, 7), new Point(8, 8));
        assertEquals(true, u.areSegmentsColinear(s3, s4));
        
        // This is not prefectly collinear but a delta of 0.5 (radius) is fine, 
        // so expected value should be true 
        Segment s5 = new Segment(new Point(1, 1), new Point(3, 2));
        Segment s6 = new Segment(new Point(5, 2), new Point(7, 3));
        assertEquals(true, u.areSegmentsColinear(s5, s6));
        
        // These segments don't lie with given delta of 0.5, 
        // so expected should be false
        Segment s7 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s8 = new Segment(new Point(5, 8), new Point(7, 5));
        assertEquals(false, u.areSegmentsColinear(s7, s8));
        
        // These segments don't lie with given delta of 0.5, 
        // so expected should be false
        Segment s9 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s10 = new Segment(new Point(100, 101), new Point(150, 4));
        assertEquals(false, u.areSegmentsColinear(s9, s10));
    }
    
    @Test
    public void calcDistanceForDashedSegmentsTest() {
        LineSegmentGA lga = new LineSegmentGA();
        segmentList = lga.initializeSegments();
        double[] actualLength = new double[2];
        
        segmentList.forEach((seg) -> {
            Point p1 = seg.leftPoint();
            Point p2 = seg.rightPoint();
            String message = "Length for each dash segment should be same.";
            assertEquals(message, 2.0, calcDistance(p1, p2), 0.0);
        });
    }
    
     @Test
    public void calcDistanceForRandomSegmentsTest() {
        // holds the result that should be the actual length for given test segments
        double[] actualDistance = {3.61, 8.25, 2.24, 2.0, 17.2, 3.16, 7.28, 7.07};
        
        segmentList = new ArrayList<>();
        Segment segment1 = new Segment(new Point(1, 3), new Point(4, 5));
        Segment segment2 = new Segment(new Point(4, 3), new Point(12, 5));
        Segment segment3 = new Segment(new Point(7, 3), new Point(5, 2));
        Segment segment4 = new Segment(new Point(10, 3), new Point(12, 3));
        Segment segment5 = new Segment(new Point(0, 1), new Point(10, 15));
        Segment segment6 = new Segment(new Point(3, 1), new Point(2, 4));
        Segment segment7 = new Segment(new Point(6, 1), new Point(8, 8));
        Segment segment8 = new Segment(new Point(9, 1), new Point(10, 8));
        
        // Add segment in 10101010 pattern
        segmentList.add(segment1);
        segmentList.add(segment2);
        segmentList.add(segment3);
        segmentList.add(segment4);
        segmentList.add(segment5);
        segmentList.add(segment6);
        segmentList.add(segment7);
        segmentList.add(segment8);
        for(int i = 0; i < segmentList.size(); i++) {
            Segment seg = segmentList.get(i);
            Point p1 = seg.leftPoint();
            Point p2 = seg.rightPoint();
            String message = "Distance between two points does not match the actual length of segment.";
            assertEquals(message, actualDistance[i], calcDistance(p1, p2), 0.0);
        }
    }
    
    private double rand() {
        Random random = new Random();
        return random.nextInt(10) / 10.0;
    }

    /**
     * Test of initializeSegments method, of class LineSegmentGA.
     */
    
    
    private int chromosomeLength = 8;
} 

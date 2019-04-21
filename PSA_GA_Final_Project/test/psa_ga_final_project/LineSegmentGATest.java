/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa_ga_final_project;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author darsh
 */
public class LineSegmentGATest {

    @Test
    public void segmentsAdjancencyTest() {
        // These tests should pass if two segments are close to each other within distance of 1.5

        // These segments are adjacent, one of the points have distance less than 1.5
        Segment s1 = new Segment(new Point(1, 2), new Point(3, 2));
        Segment s2 = new Segment(new Point(4, 2), new Point(5, 2));
        assertEquals(true, Helper.areSegmentsAdjacent(s1, s2));

        // These segments are adjacent, one of the points have distance less than 1.5
        Segment s3 = new Segment(new Point(3, 3), new Point(5, 5));
        Segment s4 = new Segment(new Point(6, 6), new Point(7, 7));
        assertEquals(true, Helper.areSegmentsAdjacent(s3, s4));

        // These segments are not adjacent (distance greater than 1.5)
        Segment s5 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s6 = new Segment(new Point(5, 8), new Point(7, 5));
        assertEquals(false, Helper.areSegmentsAdjacent(s5, s6));

        // These segments are not adjacent (distance greater than 1.5)
        Segment s7 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s8 = new Segment(new Point(100, 101), new Point(150, 4));
        assertEquals(false, Helper.areSegmentsAdjacent(s7, s8));
    }

    @Test
    public void segmentsCollinearityTest() {

        // These segments are exactly co-linear (within 0.5 radius)
        Segment s1 = new Segment(new Point(1, 2), new Point(3, 2));
        Segment s2 = new Segment(new Point(5, 2), new Point(7, 2));
        assertEquals(true, Helper.areSegmentsColinear(s1, s2));

        // These segments are exactly co-linear (within 0.5 radius)
        Segment s3 = new Segment(new Point(3, 3), new Point(5, 5));
        Segment s4 = new Segment(new Point(7, 7), new Point(8, 8));
        assertEquals(true, Helper.areSegmentsColinear(s3, s4));

        // These segments are not co-liner ( > 0.5 radius)
        Segment s7 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s8 = new Segment(new Point(5, 8), new Point(7, 5));
        assertEquals(false, Helper.areSegmentsColinear(s7, s8));

        // These segments are not co-liner ( > 0.5 radius)
        Segment s9 = new Segment(new Point(1, 5), new Point(3, 8));
        Segment s10 = new Segment(new Point(100, 101), new Point(150, 4));
        assertEquals(false, Helper.areSegmentsColinear(s9, s10));
    }

    @Test
    public void lengthOfSegmentTest() {
        LineSegmentGA lga = new LineSegmentGA();
        ArrayList<Segment> segmentList = lga.initializeSegments();

        segmentList.forEach((seg) -> {
            Point p1 = seg.leftPoint();
            Point p2 = seg.rightPoint();
            String message = "Length for each segment should be same.";
            assertEquals(message, 2.0, Helper.calcDistance(p1, p2), 0.0);
        });
    }

    @Test
    public void calcDistanceForRandomSegmentsTest() {
        // holds the result that should be the actual length for given test segments
        double[] actualDistance = {3.61, 8.25, 2.24, 2.0, 17.2, 3.16, 7.28, 7.07};

        ArrayList<Segment> segmentList = new ArrayList<>();
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
        
        for (int i = 0; i < segmentList.size(); i++) {
            Segment seg = segmentList.get(i);
            Point p1 = seg.leftPoint();
            Point p2 = seg.rightPoint();
            String message = "Distance between two` points does not match the actual length of segment.";
            assertEquals(message, actualDistance[i], Helper.calcDistance(p1, p2), 0.0);
        }
    }

    @Test
    public void calcFintnessLelvelOfSegmentListTest() {
        // Verify fitness level of segments in given list

        ArrayList<Segment> segmentList = new ArrayList<>();
        Segment segment1 = new Segment(new Point(1, 3), new Point(3, 3));
        Segment segment2 = new Segment(new Point(4, 3), new Point(6, 3));
        Segment segment3 = new Segment(new Point(7, 3), new Point(9, 3));
        Segment segment4 = new Segment(new Point(10, 3), new Point(12, 3));        

        // Add segment in 10101010 pattern
        segmentList.add(segment1);
        segmentList.add(segment2);
        segmentList.add(segment3);
        segmentList.add(segment4);        

        LineSegmentGA lsga = new LineSegmentGA();
        int actFitness = lsga.calcSegmentListFitness(segmentList);
        
        String message = "Fitness level should be 3";
        assertEquals(message, 3, actFitness, 0);
    }
}

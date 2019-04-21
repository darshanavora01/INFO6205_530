/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa_ga_final_project;

/**
 *
 * @author darsh
 */
public class Helper {    

    // Find two points which are far in distance from given two segments
    public static Point[] findFarthestTwoPoints(Segment s1, Segment s2){

        Point p1 = s1.leftPoint();
        Point p3 = s1.rightPoint();
        Point p2 = s2.leftPoint();
        Point p4 = s2.rightPoint();

        double dist1 = calcDistance(p1,p2);
        double dist2 = calcDistance(p1,p4);
        double dist3 = calcDistance(p3,p2);
        double dist4 = calcDistance(p3,p4);

        // Find max distance among four points
        double dist12 = dist1 > dist2 ? dist1 : dist2;
        double dist34 = dist3 > dist4 ? dist3 : dist4;
        double maxDistance = dist12 > dist34 ? dist12 : dist34;
        
        // Get points associated with max distance
        Point furthestPt1, furthestPt2;

        if(maxDistance == dist1) {
            furthestPt1 = p1;
            furthestPt2 = p2;
        }
        else if(maxDistance == dist2){
            furthestPt1 = p1;
            furthestPt2 = p4;
        }
        else if(maxDistance == dist3) {
            furthestPt1 = p3;
            furthestPt2 = p2;
        }
        else { //maxDistance == dist4
            furthestPt1 = p3;
            furthestPt2 = p4;
        }

        Point[] p = {furthestPt1, furthestPt2};
        return p;
     }

     // Find if two segments are co-linear or not
    public static boolean areSegmentsColinear(Segment s1, Segment s2){
        Point[] farthestPt = findFarthestTwoPoints(s1,s2);
        Point fp1 = farthestPt[0];
        Point fp2 = farthestPt[1];

        Point pt1 = s1.leftPoint();
        Point pt2 = s1.rightPoint();
        Point pt3 = s2.leftPoint();
        Point pt4 = s2.rightPoint();

        double dist1 = distanceFromLineToPoint(fp1, fp2, pt1);
        double dist2 = distanceFromLineToPoint(fp1, fp2, pt2);
        double dist3 = distanceFromLineToPoint(fp1, fp2, pt3);
        double dist4 = distanceFromLineToPoint(fp1, fp2, pt4);

        return dist1 <= radius && dist2 <= radius && dist3 <= radius && dist4 <= radius;
    }

    // Find if two segments are adjacent to each other?
    public static boolean areSegmentsAdjacent(Segment s1, Segment s2){

        Point pt1 = s1.leftPoint();
        Point pt2 = s1.rightPoint();
        Point pt3 = s2.leftPoint();
        Point pt4 = s2.rightPoint();

        double dist1 = calcDistance(pt1,pt3);
        double dist2 = calcDistance(pt1, pt4);
        double dist3 = calcDistance(pt2, pt3);
        double dist4 = calcDistance(pt2, pt4);
        
        return dist1 < segmentDist || dist2 < segmentDist || dist3 < segmentDist || dist4 < segmentDist;
    }

    // Find distance from line (from PtA to ptB) to given point ptC
    public static double distanceFromLineToPoint(Point ptA, Point ptB, Point ptC){

        double areaOfTriangle = Math.abs((((ptB.getY()-ptA.getY())*ptC.getX())-(ptB.getX()-ptA.getX())*ptC.getY())+ptB.getX()*ptA.getY() - ptB.getY()*ptA.getX());
        double distance = areaOfTriangle / calcDistance(ptA, ptB);
        return Math.abs(distance);
    }

    // Calculate distance b/w two points
    public static double calcDistance(Point p1, Point p2){
        int xDifference = p1.getX() - p2.getX();
        int yDifference = p1.getY() - p2.getY();
        
        double distance = Math.sqrt(Math.pow((xDifference),2) + Math.pow((yDifference),2));
        distance = Math.round(distance * 100.0) / 100.0;
        return distance;
    }

    //radius to check colinearity
    private static double radius = 0.5;
    //distance between two segmnets
    private static double segmentDist = 1.5;
}

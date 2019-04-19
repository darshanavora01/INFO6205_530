/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa_ga_final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author darsh
 */
public class LineSegmentGA {

    /**
     * @param args the command line arguments
     */
     public LineSegmentGA() {

    // Init data
        populationSize = 30;
        chromosomeLength = 8;
        mutationRate = 0.1;
        crossOverRate = 0.5;
        maxIterations = 500;

        segmentList = new ArrayList<>();
        population = new int[populationSize][chromosomeLength];


        segmentList = initializeSegments();
        initPopulation();
    }

    // Main()
    public static void main(String args[]) {
        LineSegmentGA ls = new LineSegmentGA();
        ls.executeLineSegmentGA();
    }

    // Instantiate Segments and add them to list
    public ArrayList<Segment> initializeSegments() {

        Segment segment1 = new Segment(new Point(1, 3), new Point(3, 3));
        Segment segment2 = new Segment(new Point(4, 3), new Point(6, 3));
        Segment segment3 = new Segment(new Point(7, 3), new Point(9, 3));
        Segment segment4 = new Segment(new Point(10, 3), new Point(12, 3));
        Segment segment5 = new Segment(new Point(0, 1), new Point(2, 1));
        Segment segment6 = new Segment(new Point(3, 1), new Point(5, 1));
        Segment segment7 = new Segment(new Point(6, 1), new Point(8, 1));
        Segment segment8 = new Segment(new Point(9, 1), new Point(11, 1));

        // Add segment in 10101010 pattern
        segmentList.add(segment1);
        segmentList.add(segment5);
        segmentList.add(segment2);
        segmentList.add(segment6);
        segmentList.add(segment3);
        segmentList.add(segment7);
        segmentList.add(segment4);
        segmentList.add(segment8);

        return segmentList;
    }

    // Run Line Segment detection Genetic Algorithm
    private void executeLineSegmentGA() {

        int accept, reject;

        for (int n = 0; n < maxIterations; n++) {

            int element1 = (int) (populationSize * rand());
            int element2 = (int) (populationSize * rand());


            // Make sure both elements are different
            while (element1 == element2) {
                element2 = (int) (populationSize * rand());
            }

            // Calculate chromosome Fitness
            if (calcChromosomeFitness(element1) > calcChromosomeFitness(element2)) {
                accept = element1;
                reject = element2;
            } else {
                accept = element2;
                reject = element1;
            }

            //GA operation for failed elements
            for (int i = 0; i < chromosomeLength; i++) {

                //Do cross-over operation
                doCrosover(reject, accept, i);

                //Do mutation operation
                doMutation(reject, i);

                //Check if updated chromosome fitness level is as expected (-1)
                if (calcChromosomeFitness(reject) == -1) {
                    printResult(reject, n);
                    return;
                }
            }
        }
    }

    // Perform cross-over operation from b to a chromosome on nth element from population
    private void doCrosover(int a, int b, int n) {
        if (rand() < crossOverRate) {
            population[a][n] = population[b][n];
        }
    }

    // Perform mutation operation on nth chromosome from population
    private void doMutation(int a, int n) {
        if (rand() < mutationRate) {
            population[a][n] = 1 - population[a][n];
        }
    }

    // Print final result - segments that forms a line
    private void printResult(int a, int n) {
        System.out.println("\n******************************************\n");
        System.out.println("After " + n + " iterations, segments that make line are: \n");

        System.out.println("**First line segments are:**\n");
        for (int i = 0; i < chromosomeLength; i++) {
            if (population[a][i] == 0) {
                printSegment(segmentList.get(i));
            }
        }

        System.out.println();
        System.out.println("**Second line segments are:**\n");
        for (int i = 0; i < chromosomeLength; i++) {
            if (population[a][i] == 1) {
                printSegment(segmentList.get(i));
            }
        }
    }

    //Print segment points
    private void printSegment(Segment s) {
        Point point1 = s.leftPoint();
        Point point2 = s.rightPoint();

        int x1 = (int) point1.getX();
        int x2 = (int) point2.getX();
        int y1 = (int) point1.getY();
        int y2 = (int) point2.getY();

        System.out.println("Segment: (" + x1 + "," + y1 + ")" + ", (" + x2 + "," + y2 + ")");
    }

    // Calculate fitness value of given chromosome
    private int calcChromosomeFitness(int n) {

        List<Segment> list1 = new ArrayList<>();
        List<Segment> list2 = new ArrayList<>();

        for (int i = 0; i < chromosomeLength; i++) {

            if (population[n][i] == 0) {
                list1.add(segmentList.get(i));
            } else {
                list2.add(segmentList.get(i));
            }
        }
        System.out.print("Chromosome: ");

        for (int j = 0; j < chromosomeLength; j++) {
            System.out.print(population[n][j] + " ");
        }
        System.out.println();

        //Check how fit the population is
        int result1 = calcSegmentListFitness(list1);
        int result2 = calcSegmentListFitness(list2);

        if (result1 == 3 && result2 == 3) {
            return -1;
        } else {
            return (result1 + result2);
        }
    }

    //Calculate fitness level of segments in given list
    private int calcSegmentListFitness(List<Segment> segList) {

        int fitness = 0;

        for (int i = 0; i < (segList.size() - 1); i++) {
            Segment seg1 = segList.get(i);
            Segment seg2 = segList.get(i + 1);

            // If two segments are coliner and adjacent then we found a pair to form a line
            if (Utility.areSegmentsColinear(seg1, seg2) && Utility.areSegmentsAdjacent(seg1, seg2))
                fitness++;
        }
        return fitness;
    }

    // Initialize population with random chromosome value
    private void initPopulation() {
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < chromosomeLength; j++) {

                //Random assignment of chromosome value
                if (rand() < 0.5) {
                    population[i][j] = 0;
                } else {
                    population[i][j] = 1;
                }
            }
        }
    }

    // Generated random double in 0.0 to 1.0 range
    private double rand() {
        Random random = new Random();
        return random.nextInt(10) / 10.0;
    }


    private int populationSize;
    private int chromosomeLength;
    private double mutationRate;
    private double crossOverRate;
    private int maxIterations;
    private ArrayList<Segment> segmentList;
    private int[][] population;

}

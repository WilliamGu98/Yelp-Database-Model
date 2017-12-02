package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;
import com.google.gson.*;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class KMeansClusterTest {

	public static class clusterClass {
		private double x;
		private double y;
		private int cluster;

		public double getX() {
			return this.x;
		}

		public double getY() {
			return this.y;
		}

		public int getCluster() {
			return this.cluster;
		}

		/**
		 * 
		 * @param list
		 *            - list of all clusterClass objects
		 * @param k
		 *            - cluster number you want to retrieve all restaurants for
		 * @return list of retaurants within cluster k
		 */
		public static List<clusterClass> getRestaurantsInCluster(clusterClass[] list, int k) {
			List<clusterClass> clusterList = new ArrayList<clusterClass>();
			for (int i = 0; i < list.length; i++) {
				if (list[i].getCluster() == k) {
					clusterList.add(list[i]);
				}
			}
			return clusterList;
		}

		/**
		 * Calculates the centroid of the cluster by taking the mean of the x
		 * coordinates and the mean of the y coordinates
		 * 
		 * @param list
		 *            - list of clusterClass objects which belong to the same cluster
		 * @return a size-2 array where [0] = x coordinate and [1] = y coordinate
		 */
		public static double[] findCentroid(List<clusterClass> list) {
			List<Double> xList = new ArrayList<Double>();
			List<Double> yList = new ArrayList<Double>();
			Double sumX = 0.0;
			Double sumY = 0.0;
			double centroid[] = new double[2];
			for (clusterClass cluster : list) {
				xList.add(cluster.getX());
				yList.add(cluster.getY());
			}
			for (Double x : xList) {
				sumX += x;
			}
			for (Double y : yList) {
				sumY += y;
			}
			centroid[0] = sumX / xList.size();
			centroid[1] = sumY / yList.size();
			return centroid;
		}

		/**
		 * 
		 * @param centroid
		 *            - an array where index 0 is the x coordinate of the centroid and
		 *            index 1 is the y-coordinate of the centroid
		 * @return the distance from a restaurant to the given centroid
		 */
		public double findDistanceToCentroid(double[] centroid) {
			double x = this.getX();
			double y = this.getY();
			double deltaX = Math.abs(centroid[0] - x);
			double deltaY = Math.abs(centroid[1] - y);
			double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
			return distance;
		}
		public boolean testMethod() {
			return true;
		}
	}

	@Test
	public void test1() throws IOException {

		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		int k = 1;
		String s1 = myDatabase.kMeansClusters_json(k);
		List<double[]> centroidList = new ArrayList<double[]>();

		Gson gson = new Gson();
		clusterClass[] clusters = gson.fromJson(s1, clusterClass[].class);
		List<clusterClass> specificClusterList = new ArrayList<clusterClass>();

		double shortestDistance;

		for (int i = 0; i < k; i++) {	//find all centroids. put them in a list
			specificClusterList = clusterClass.getRestaurantsInCluster(clusters, i);
			double[] centroid = clusterClass.findCentroid(specificClusterList);
			centroidList.add(centroid);
		}
		for (clusterClass restaurant : clusters) { // for EVERY restaurant
			shortestDistance = Double.MAX_VALUE;

			for (int i = 0; i < k; i++) { // retrieve the shortest distance from restaurant to cluster
				double distance = restaurant.findDistanceToCentroid(centroidList.get(i));
				if (distance < shortestDistance) {
					shortestDistance = distance;
				}
			}
			// find the distance to it's own centroid
			int thisCluster = restaurant.getCluster();
			double[] thisCentroid = centroidList.get(thisCluster);
			double thisDistance = restaurant.findDistanceToCentroid(thisCentroid);

			assertEquals(thisDistance, shortestDistance, 0.0000000001);
		}
		// System.out.println(myDatabase.kMeansClusters_json(5));
	}
	
	@Test
	public void test2() throws IOException {

		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		int k = 10;
		String s1 = myDatabase.kMeansClusters_json(k);
		List<double[]> centroidList = new ArrayList<double[]>();

		Gson gson = new Gson();
		clusterClass[] clusters = gson.fromJson(s1, clusterClass[].class);
		List<clusterClass> specificClusterList = new ArrayList<clusterClass>();

		double shortestDistance;

		for (int i = 0; i < k; i++) {	//find all centroids. put them in a list
			specificClusterList = clusterClass.getRestaurantsInCluster(clusters, i);
			double[] centroid = clusterClass.findCentroid(specificClusterList);
			centroidList.add(centroid);
		}
		for (clusterClass restaurant : clusters) { // for EVERY restaurant
			shortestDistance = Double.MAX_VALUE;

			for (int i = 0; i < k; i++) { // retrieve the shortest distance from restaurant to cluster
				double distance = restaurant.findDistanceToCentroid(centroidList.get(i));
				if (distance < shortestDistance) {
					shortestDistance = distance;
				}
			}
			// find the distance to it's own centroid
			int thisCluster = restaurant.getCluster();
			double[] thisCentroid = centroidList.get(thisCluster);
			double thisDistance = restaurant.findDistanceToCentroid(thisCentroid);

			assertEquals(thisDistance, shortestDistance, 0.0000000001);
		}
		// System.out.println(myDatabase.kMeansClusters_json(5));
	}
}

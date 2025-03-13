package com.example.evmanagement.util;

import java.util.*;

/**
 * Dummy implementation for Dijkstra's Algorithm.
 * In a full implementation, you would build and traverse a weighted graph.
 */
public class DijkstraUtil {

    // Example graph representation (adjacency list)
    private static final Map<String, Map<String, Double>> graph = new HashMap<>();

    static {
        // Example connections and distances (replace with your actual data)
        graph.put("hellop", Map.of("ktm", 10.0, "bkt", 15.0));
        graph.put("ktm", Map.of("hellop", 10.0, "bkt", 5.0, "pkr", 200.0));
        graph.put("bkt", Map.of("hellop", 15.0, "ktm", 5.0));
        graph.put("pkr", Map.of("ktm", 200.0));
    }

    public static String calculateOptimalRoute(String start, String destination) {
        if (!graph.containsKey(start) || !graph.containsKey(destination)) {
            return "Start or destination not found.";
        }

        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (String node : graph.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(destination)) {
                break; // Found the destination
            }

            if (!graph.containsKey(current)) {
                continue;
            }

            for (Map.Entry<String, Double> neighborEntry : graph.get(current).entrySet()) {
                String neighbor = neighborEntry.getKey();
                double distance = neighborEntry.getValue();
                double newDistance = distances.get(current) + distance;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct the path
        if (previous.containsKey(destination)) {
            List<String> path = new ArrayList<>();
            String current = destination;
            while (current != null) {
                path.add(0, current);
                current = previous.get(current);
            }
            return String.join(" -> ", path);
        } else {
            return "No route found.";
        }
    }

    // Method to get all locations from the graph
    public static Set<String> getLocations() {
        return graph.keySet();
    }
}
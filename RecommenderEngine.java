import java.io.*;
import java.util.*;

public class RecommenderEngine {

    private Map<Long, Map<Long, Double>> userRatings = new HashMap<>();

    public RecommenderEngine() {
        loadRatings("data/user_ratings.csv");
    }

    private void loadRatings(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                long userId = Long.parseLong(parts[0]);
                long itemId = Long.parseLong(parts[1]);
                double rating = Double.parseDouble(parts[2]);

                userRatings.putIfAbsent(userId, new HashMap<>());
                userRatings.get(userId).put(itemId, rating);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map.Entry<Long, Double>> getRecommendations(long targetUserId, int topN) {
        if (!userRatings.containsKey(targetUserId)) return Collections.emptyList();

        Map<Long, Double> targetRatings = userRatings.get(targetUserId);
        Map<Long, Double> scores = new HashMap<>();

        for (Map.Entry<Long, Map<Long, Double>> entry : userRatings.entrySet()) {
            long otherUserId = entry.getKey();
            if (otherUserId == targetUserId) continue;

            double similarity = cosineSimilarity(targetRatings, entry.getValue());
            for (Map.Entry<Long, Double> itemRating : entry.getValue().entrySet()) {
                long itemId = itemRating.getKey();
                if (!targetRatings.containsKey(itemId)) {
                    scores.put(itemId, scores.getOrDefault(itemId, 0.0) + similarity * itemRating.getValue());
                }
            }
        }

        List<Map.Entry<Long, Double>> sorted = new ArrayList<>(scores.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        return sorted.subList(0, Math.min(topN, sorted.size()));
    }

    private double cosineSimilarity(Map<Long, Double> a, Map<Long, Double> b) {
        double dot = 0.0, magA = 0.0, magB = 0.0;

        for (long key : a.keySet()) {
            magA += Math.pow(a.get(key), 2);
            if (b.containsKey(key)) {
                dot += a.get(key) * b.get(key);
            }
        }

        for (double val : b.values()) {
            magB += Math.pow(val, 2);
        }

        if (magA == 0 || magB == 0) return 0;
        return dot / (Math.sqrt(magA) * Math.sqrt(magB));
    }
}

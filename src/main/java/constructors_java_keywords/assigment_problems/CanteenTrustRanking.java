package main.java.constructors_java_keywords.assigment_problems;

import java.util.Arrays;

public class CanteenTrustRanking {

    static class Canteen {
        private String canteenCode, canteenName;
        private int trustScore;

        public Canteen(String canteenCode,
                       String canteenName,
                       int trustScore) {

            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        int compareTo(Canteen other) {
            if (this.trustScore != other.trustScore)
                return Integer.compare(other.trustScore,
                                       this.trustScore);

            int code =
                this.canteenCode.compareToIgnoreCase(other.canteenCode);

            if (code != 0)
                return code;

            return Integer.compare(this.canteenName.length(),
                                   other.canteenName.length());
        }

        static Canteen[] rankCanteens(Canteen[] canteens) {
            Canteen[] a = canteens.clone();

            for (int i = 1; i < a.length; i++) {
                Canteen key = a[i];
                int j = i - 1;

                while (j >= 0 && a[j].compareTo(key) > 0) {
                    a[j + 1] = a[j];
                    j--;
                }

                a[j + 1] = key;
            }

            return a;
        }
    }

    public static void main(String[] args) {
        Canteen[] c = {
            new Canteen("HB3-C","Spice Junction",3),
            new Canteen("hb1-c","Grand Mess",5),
            new Canteen("HB2-C","Southern Treats")
        };

        Canteen[] ranked = Canteen.rankCanteens(c);

        String[] result = new String[ranked.length];

        for (int i = 0; i < ranked.length; i++)
            result[i] = ranked[i].canteenCode;

        System.out.println(Arrays.toString(result));
    }
}

package main.java.constructors_java_keywords.class_problems;

import java.util.Arrays;

public class BusRouteRankingEngine {
    static class BusRoute {
        private String routeCode, routeName;
        private int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 0);
        }

        int compareTo(BusRoute other) {
            if (this.priority != other.priority)
                return Integer.compare(other.priority, this.priority);

            int x = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (x != 0)
                return x;

            return this.routeName.compareToIgnoreCase(other.routeName);
        }

        static BusRoute[] rankRoutes(BusRoute[] routes) {
            BusRoute[] a = routes.clone();

            for (int i = 1; i < a.length; i++) {
                BusRoute key = a[i];
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
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = BusRoute.rankRoutes(routes);

        String[] result = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++)
            result[i] = ranked[i].routeCode;

        System.out.println(Arrays.toString(result));
    }
}

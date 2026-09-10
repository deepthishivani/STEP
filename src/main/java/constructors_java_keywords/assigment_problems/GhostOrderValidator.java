package main.java.constructors_java_keywords.assigment_problems;

public class GhostOrderValidator {

    static class FoodOrder {
        private String studentName, dishName;
        private boolean delivered;

        public FoodOrder(String studentName, String dishName) {
            if (studentName == null || studentName.trim().isEmpty())
                throw new IllegalArgumentException("Invalid student name");

            if (dishName == null || dishName.trim().isEmpty())
                throw new IllegalArgumentException("Invalid dish name");

            this.studentName = studentName.trim();
            this.dishName = dishName.trim();
        }

        void markDelivered() {
            if (delivered)
                System.out.println("Order already delivered");
            else {
                delivered = true;
                System.out.println("Order marked delivered");
            }
        }

        static void processBatch(String[][] rawOrders) {
            int valid = 0, rejected = 0;

            for (String[] r : rawOrders) {
                try {
                    if (r == null || r.length < 2)
                        throw new IllegalArgumentException();

                    new FoodOrder(r[0], r[1]);
                    valid++;

                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            System.out.println("Valid: " + valid +
                    " | Rejected: " + rejected);
        }
    }

    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi","Paneer Butter Masala"},
            {"","Chole Bhature"},
            {"Meera"," "},
            {"Divya","Veg Biryani"}
        };

        FoodOrder.processBatch(orders);
    }
}

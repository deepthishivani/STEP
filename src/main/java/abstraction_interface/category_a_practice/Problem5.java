package abstraction_interface.category_a_practice;

abstract class LibraryItem {
    private static int nextId = 1000;
    private final String itemId;

    protected LibraryItem() {
        itemId = "LI-" + (++nextId);
    }

    public String getItemId() {
        return itemId;
    }

    public abstract int getLoanPeriodDays();
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem implements Renewable, Reservable {
    private final String title;

    public Textbook(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Blank title");
        }
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem implements Renewable {
    private final String title;

    public Magazine(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Blank title");
        }
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {
    private final String resourceName;

    public DigitalPass(String resourceName) {
        if (resourceName == null || resourceName.isBlank()) {
            throw new IllegalArgumentException("Blank resource name");
        }
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

public class Problem5 {
    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            System.out.println(item.getLoanPeriodDays());
        }
    }

    static String reserveIfSupported(Object item) {
        if (item instanceof Reservable) {
            return ((Reservable) item).reserve();
        }
        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Textbook textbook = new Textbook("Java Fundamentals");
        Magazine magazine = new Magazine("Tech Monthly");
        DigitalPass pass = new DigitalPass("E-Journal Access");

        System.out.println(textbook.getLoanPeriodDays());
        System.out.println(textbook.renew());
        System.out.println(textbook.reserve());
        System.out.println(reserveIfSupported(magazine));
        System.out.println(reserveIfSupported(pass));

        LibraryItem ref = textbook;
        System.out.println(reserveIfSupported(ref));
        processCheckouts(new LibraryItem[]{textbook, magazine});
    }
}

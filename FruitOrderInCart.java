import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 
 */
public class FruitOrderInCart {


    /**
     * Determine if customer should receive an offer.
     */
    static public boolean receiveOffer0(List<List<String>> offerItems, List<String> cartItems) {

        // **** sanity checks ****
        if (offerItems.size() == 0) return false;

        // **** convert offerList to string ****
        String offerStr = OffersToStr(offerItems);

        // **** convert cart to string ****
        String cartStr = CartToStr(cartItems);

        // **** array of offers ****
        String[] offers = offerStr.split(" ");

        // **** array or cart items ****
        String[] items = cartStr.split(" ");

        // **** compare contents in arrays ****
        return GetOffer(offers, items);
    }


    /**
     * Return a string of offers blank separated.
     */
    static private String OffersToStr(List<List<String>> offerItems) {

        // **** initialization ****
        StringBuilder sb = new StringBuilder();

        // **** build string ****
        for (List<String> lst : offerItems) {
            for (String s : lst)
                sb.append(s + ' ');
        }

        // **** delete last ' ' ****
        sb.delete(sb.length() - 1, sb.length());

        // **** return offer string blank separated ****
        return sb.toString();
    }


    /**
     * Return string with cart items.
     */
    static private String CartToStr(List<String> cartItems) {

        // **** initialization ****
        StringBuilder sb = new StringBuilder();

        // **** build string with cart contents ****
        for (String item : cartItems)
            sb.append(item + ' ');

        // **** delete last ' ' ****
        sb.delete(sb.length() - 1, sb.length());

        // **** return cart string blank separated ****
        return sb.toString();
    }


    /**
     * Compare array contents.
     */
    static private boolean GetOffer(String[] offers, String[] items) {

        // **** sanity checks ****
        if (offers.length != offers.length) return false;

        // **** check offers and items ****
        for (int i = 0; i < offers.length; i++) {

            // **** skip checking this item ****
            if (offers[i].equals("anything")) continue;

            // **** ****
            if (!offers[i].equals(items[i])) return false;
        }

        // **** all items match the offers in the same order ****
        return true;
    }


    /**
     * Determine if customer should receive an offer.
     */
    static public boolean receiveOffer(List<List<String>> offerItems, List<String> cartItems) {

        // **** sanity checks ****
        if (offerItems.size() == 0) return false;

        // **** count number of items in offerItems ****
        int m = 0;
        for (List<String> lst : offerItems)
            for (String s : lst)
                m++;

        // **** declare offers array ****
        String[] offers = new String[m];

        // **** populate offers array ****
        int j = 0;
        for (List<String> lst : offerItems)
            for (String s : lst)
                offers[j++] = s;

        // **** get number of items ****
        int n = cartItems.size();

        // **** cart items array ****
        String[] items = new String[n];

        // **** populate array ****
        for (int i = 0; i < n; i++)
            items[i] = cartItems.get(i);

        // **** check offers and items ****
        for (int i = 0; i < offers.length; i++) {

            // **** skip checking this item ****
            if (offers[i].equals("anything")) continue;

            // **** item does not match ****
            if (!offers[i].equals(items[i])) return false;
        }

        // **** offers match items in cart ****
        return true;
    }


    /**
     * Test scaffold.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // *** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** number of offer elements ****
        int n = Integer.parseInt(br.readLine().trim());

        // **** read ofer elements into List<List<String>> ****
        List<List<String>> offerItems = new ArrayList<List<String>>();
        for (int i = 0; i < n; i++) {

            // **** read and populate list ****
            List<String> lst = Arrays.stream(br.readLine().trim().split(" "))
                                    .collect(Collectors.toList());

            // **** add list to List<List<String>> ****
            offerItems.add(lst);
        }

        // **** number of products in cart ****
        int m = Integer.parseInt(br.readLine().trim());

        // **** read products into cart ****
        List<String> cart = new ArrayList<>();
        for (int i = 0; i < m; i++)
            cart.add(br.readLine().trim());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<         n: " + n);
        System.out.println("main <<< offerList: " + offerItems.toString());
        System.out.println("main <<<         m: " + m);
        System.out.println("main <<<      cart: " + cart.toString());

        // **** call function of interest and display tesult ****
        System.out.println("main <<< receiveOffer0: " + receiveOffer0(offerItems, cart));

        // **** call function of interest and display tesult ****
        System.out.println("main <<<  receiveOffer: " + receiveOffer(offerItems, cart));
    }

}
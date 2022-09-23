import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class PaginationHelper <I> {
    public List<I> collection;
    int itemsPerPage;

    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.itemsPerPage=itemsPerPage;
        this.collection=collection;
    }

    public int itemCount() {
        return  collection.size();
    }


    public int pageCount() {
        return (((collection.size()-1)/itemsPerPage)+1);
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if(pageIndex>=pageCount() || pageIndex<0){
            return -1;
        }
        else if((pageIndex+1)==pageCount()){
            return itemCount()-((pageCount()-1) * itemsPerPage);
        }
        else{
            return itemsPerPage;
        }
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex>collection.size()-1 || itemIndex<0){
            return -1;
        }
        else{
           return itemIndex/itemsPerPage;
        }
    }




}

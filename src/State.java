import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

final public class State {
    private static ArrayList<PhotoCollection> collections = new ArrayList<>();
    private static PhotoCollection activeCollection = null;

    static S30208Leshchenko frame;
    static AsideView asideView;
    static MainView mainView;
    static SearchBarView searchBarView;
    static PhotoListView photoListView;
    static PhotoListNavbarView photoListNavbarView;

    static String appFontName = "Calibri";

    static PhotoCollection createCollection(String name) {
        PhotoCollection collection = new PhotoCollection(name);
        State.collections.add(collection);
        return collection;
    }

    static ArrayList<PhotoCollection> getCollections() {
        return State.collections;
    }

    static LocalDate getActiveCollectionCreationDate() {
        if(State.activeCollection != null) {
            return State.activeCollection.createdAt.toLocalDateTime().toLocalDate();
        }
        return null;
    }

    static void clearCollections() {
        State.collections.clear();
    }

    static void removeCollection(String collectionId) {
        for (int i = 0; i < State.collections.size(); i++) {
            if(State.collections.get(i).id.equals(collectionId)) {
                State.collections.remove(i);
                break;
            }
        }
    }

    static PhotoCollection getActiveCollection() {
        return State.activeCollection;
    }

    static void setActiveCollection(PhotoCollection collection) {
        State.activeCollection = collection;
    }

}

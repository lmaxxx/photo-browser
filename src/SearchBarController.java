public class SearchBarController {
    SearchBarView searchBarView;

    SearchBarController(SearchBarView searchBarView) {
        this.searchBarView = searchBarView;
        setActions();
    }

    void setActions() {
        this.searchBarView.searchButton.addActionListener(_ -> this.search());
        this.searchBarView.cancelButton.addActionListener(_ -> {
            this.cancelFilter();
            Controllers.photoGridController.updatePhotoGrid();
        });
    }

    void search() {
        State.filterPhotos(
                (String)this.searchBarView.dropDownMenu.getItemAt(this.searchBarView.dropDownMenu.getSelectedIndex()),
                this.searchBarView.input.getText()
        );
        State.showFilteredPhotos = true;
        this.searchBarView.updateSearchBar();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void cancelFilter() {
        State.clearFilteredPhotos();
        State.showFilteredPhotos = false;
        this.searchBarView.input.setText("");
        this.searchBarView.updateSearchBar();
    }
}

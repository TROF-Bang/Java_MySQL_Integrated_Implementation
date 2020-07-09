package appBody;

import java.net.URL;
import java.util.ResourceBundle;

import boardDB.Board;
import boardDB.BoardDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController implements Initializable{

	@FXML TextField txtTitle;
	@FXML PasswordField txtPassword;
	@FXML ComboBox<String> comboPublic;
	@FXML TextArea txtContent;
	@FXML TextField textField;
	@FXML TextField writerName;
	
	BoardDao dao = new BoardDao();
//	ObservableList<String> list = FXCollections.observableArrayList("공개", "비공개");
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		comboPublic.setItems(list);	
	}

	@FXML
	public void handleBtnRegAction() {
		dao.insertStudent(new Board(txtTitle.getText(), txtPassword.getText(), comboPublic.getSelectionModel().getSelectedItem().toString(), writerName.getText(), txtContent.getText()));
		txtTitle.clear();
		txtPassword.clear();
		comboPublic.getSelectionModel().clearSelection();
		txtContent.clear();
		textField.clear();
		writerName.clear();
	}
	
	@FXML
	public void handleBtnClear() {
		txtTitle.clear();
		txtPassword.clear();
		comboPublic.getSelectionModel().clearSelection();
		txtContent.clear();
		textField.clear();
		writerName.clear();
	}
	
	@FXML
	public void handleBtnSelect() {
		System.out.println(textField.getText());
		
		Board board= dao.selectOne(textField.getText());
		System.out.println(board.getBorderTitle());
		txtTitle.setText(board.getBorderTitle());
		txtPassword.setText(board.getBorderPassword());
		comboPublic.setId(board.getComboPublic());
		txtContent.setText(board.getTextContent());
		textField.clear();
		writerName.setText(board.getWriterName());
	}
}

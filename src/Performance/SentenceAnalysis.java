package Performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import zemberek.tokenization.TurkishSentenceExtractor;

public class SentenceAnalysis {
	private List<String> list = new ArrayList<String>();
	private ArrayList<String> txtList = new ArrayList<String>();

	public List<String> Sentence(String txt) throws IOException {
		
		list.clear();
		txtList.clear();
		
		list.add(txt);
		for (String lst : list) {
			txt = lst.toLowerCase();
		}

		TurkishSentenceExtractor ekstra = TurkishSentenceExtractor.DEFAULT;
		list = ekstra.fromParagraph(txt);

		for (int i = 0; i < list.size(); i++) {
			String[] str2 = list.get(i).split("(, ,)|(\\[)|(\\])");
			for (int j = 0; j < str2.length; j++)
				if (str2[j].contains(" "))
					txtList.add(str2[j]);
		}
		
//		for (int i = 0; i < txtList.size(); i++) {
//			System.out.println(txtList.get(i));
//		}
		
		return txtList;
	}
}

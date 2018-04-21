package Performance;

import java.io.IOException;
import java.util.ArrayList;

public class AccuracyCalculating {

	private static ArrayList<String> StrTextList = new ArrayList<String>();
	private static ArrayList<String> UsrSumTextList = new ArrayList<String>();
	private static ArrayList<String> SysSumTextList = new ArrayList<String>();

	private static String StrText;
	private static String UsrSumText;
	private static String SysSumText;

	private static float TN = 0, TP = 0, FP = 0, FN = 0;
	
	public static void main(String[] args) throws IOException {
		FileOperations FRW = new FileOperations();
		SentenceAnalysis SA = new SentenceAnalysis();

		AccuracyCalculating AC = new AccuracyCalculating();

		// OrjinalMetin
		for (int i = 1; i < 2; i++) {
			StrText = FRW.FileRead("C:/YeniMetinler/TumMetinler/" + i + ".txt");
			StrTextList.addAll(SA.Sentence(StrText));
		}

		// KullanıcıÖzetiOlanMetin
		for (int i = 1; i < 2; i++) {
			UsrSumText = FRW.FileRead("C:/YeniMetinler/Ozetler/" + i + "koz.txt");
			UsrSumTextList.addAll(SA.Sentence(UsrSumText));
		}

		// SistemÖzetiOlanMetin
		for (int i = 1; i < 2; i++) {
			SysSumText = FRW.FileRead("C:/YeniMetinler/Ozetler/" + i + "o.txt");
			SysSumTextList.addAll(SA.Sentence(SysSumText));
		}

		AC.TrueNegative();
		AC.TruePossitive();
		AC.FalseNegative();
		AC.FalsePossitive();
		
		float acc = (TP + TN) / (TP + FP + TN +FN);
		System.out.println("Accuracy: %" + acc*100);
	}

	private void TrueNegative() {
		for(String line: StrTextList) {
			if (!SysSumTextList.contains(line) && !UsrSumTextList.contains(line))
				TN++;
		}
		System.out.println("True Negative: " + TN);
	}
	private void TruePossitive() {
		for(String line: UsrSumTextList) {
			if(SysSumTextList.contains(line)) {
				TP++;
			}
		}

		System.out.println("True Possitive: " + TP);
	}
	private void FalseNegative() {
		for(String line: UsrSumTextList) {
			if(!SysSumTextList.contains(line)) {
				FN++;
			}
		}
		FN--;
		System.out.println("False Negative: " + FN);
	}
	private void FalsePossitive() {
		for(String line: SysSumTextList) {
			if(!UsrSumTextList.contains(line)) {
				FP++;
			}
		}
		FP--;
		System.out.println("False Positive: " + FP);
	}
}

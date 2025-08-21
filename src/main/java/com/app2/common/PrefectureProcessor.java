package com.app2.common;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrefectureProcessor {

    private static final List<String> PREFECTURES = List.of(
        "北海道", "青森", "岩手", "宮城", "秋田", "山形", "福島", "茨城", "栃木", "群馬",
        "埼玉", "千葉", "東京", "神奈川", "新潟", "富山", "石川", "福井", "山梨", "長野",
        "岐阜", "静岡", "愛知", "三重", "滋賀", "京都", "大阪", "兵庫", "奈良", "和歌山",
        "鳥取", "島根", "岡山", "広島", "山口", "徳島", "香川", "愛媛", "高知", "福岡",
        "佐賀", "長崎", "熊本", "大分", "宮崎", "鹿児島", "沖縄"
    );

    // 都道府県を処理するメインメソッド
    public List<String> processPrefectures(String numStr) {
        // 入力が無効である場合
        // if (isInvalidInput(numStr)) {
        //     return new ArrayList<>();
        // }

        // 入力文字列を整数に変換
        int preNum = parseInput(numStr);
        // 変換した整数が有効な範囲内かをチェック
        if (preNum < 1 || preNum > PREFECTURES.size()) {
            // return new ArrayList<>();
            throw new InvalidParameterException("入力が無効です");
        }

        // ランダムに都道府県を選択し、リストに追加
        return selectRandomPrefectures(preNum);
    }

    // 入力文字列がnullまたは空であるかをチェックするメソッド
    private boolean isInvalidInput(String numStr) {
        return numStr == null || numStr.isEmpty();
    }

    // 入力文字列を整数に変換するメソッド
    private int parseInput(String numStr) {
        return Integer.parseInt(numStr);
    }

    // ランダムに都道府県を選択し、リストに追加するメソッド
    private List<String> selectRandomPrefectures(int preNum) {
        List<String> nameList = new ArrayList<>(PREFECTURES);
        List<String> chosenList = new ArrayList<>();
        Random rand = new Random();

        // 指定された数だけランダムに都道府県を選択
        for (int i = 0; i < preNum; i++) {
            int randomIndex = rand.nextInt(nameList.size());
            String chosenPrefecture = nameList.get(randomIndex);
            chosenList.add(chosenPrefecture);
            nameList.remove(randomIndex);
        }

        return chosenList;
    }
}

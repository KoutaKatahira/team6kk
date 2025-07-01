package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Repository.UserRepository;

@Controller
public class RoutesearchController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    
    int userID = 1;//ID割り当て
    int gouserID = 1;
    int menuID = 3; //メニュー用ID割り当て
    int quantity = 3;   //商品の数
    String callMessage = "お待たせしました！！\\nご注文いただいた商品が完成いたしました。\\n本店までお越しください。"; //呼び出しメッセージ
    String subMessage = "予約注文していた商品が完成しました！！";
    //final MailSender mailSender = null;

    //トップに戻る
    @GetMapping("/index")
    private String display(Model model){
        
        /*
        String menu1 = "カレーライス(500円)";
        String menu2 = "ラーメン(500円)";
        String menu3 = "ショートケーキ(500円)";
        
        //DBで作り直す予定です。
        
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("itemname", menu1);
        resultList.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("itemname", menu2);
        resultList.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("itemname", menu3);
        resultList.add(map3);
        model.addAttribute("resultList", resultList);
        
        */
        System.out.println("リストの数:"+menuID);
        //DBを使用する
        List<Map<String, Object>> resultList = userRepository.sort(quantity);
        Collections.reverse(resultList);
        model.addAttribute("resultList", resultList);
        
        return "index.html";
    }
    
    //管理者ログイン画面
    @GetMapping("/login")
    private String display3(){
        return "login.html";
    }
    
  //削除確認画面へ
    @GetMapping("/reset2")
    private String display9(){
        return "reset2.html";
    }
    
    //検索結果
    @RequestMapping(path = "/result", method = RequestMethod.GET)
    public String display4(Model model) {
        return "result";
    }
    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public String display4(String name, String address, String rad1, Integer number1, String story, Model model) {

        if (number1 == null) {
            number1 = 0; // 例：デフォルト値
        }
        
        userID = userRepository.goid();
        //for(userID = 1; userID < gouserID; userID++) {
        //}
        //注文情報を登録する
        userID++; //IDの値を1プラスする。1人1人に異なるIDを割り当てる。
        int resultList = userRepository.findByCategory(name,address,rad1,number1,story,userID);
        System.out.println(name);
        System.out.println(userID);
        System.out.println(gouserID);
        model.addAttribute("resultList", resultList);

        return "result";
    }
    
  //データ削除
    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String display5(Model model) {
      //注文情報を全削除する
        int resultList = userRepository.deleteData();
        userID = 1; //IDをリセットする。
        System.out.println("\nデータ消えた！！");
        //model.addAttribute("resultList", resultList);
        return "delete";
    }
    
    //注文履歴
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String display2(Model model){
        
        //DBを使用する
        List<Map<String, Object>> resultList = userRepository.findAll();
        model.addAttribute("resultList", resultList);
        
        return "list";
    }
    //ログイン結果用
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public String display2(String username,String pass,Model model) {

      //DBを使用する
        List<Map<String, Object>> resultList = userRepository.findAll();
        model.addAttribute("resultList", resultList);
        
        int resultTest = userRepository.goLogin(username,pass);
        
        model.addAttribute("resultList", resultList);
        System.out.println("\nID:"+username);
        System.out.println("PASS:"+pass);
        System.out.println("発見:"+resultTest);

        if(resultTest >= 1) {
        return "list";
        }else {
            return "miss";
        }
    }
    
  //呼び出し1
    @RequestMapping(path = "/call", method = RequestMethod.GET)
    public String display6(Model model){
        return "call";
    }
    //呼び出し2
    @RequestMapping(path = "/call", method = RequestMethod.POST)
    public String display6(int callNo,Model model) {

      //DBを使用する
        List<Map<String, Object>> resultList = userRepository.findAll();
        //model.addAttribute("resultList", resultList);
        String name2 = userRepository.findCall(callNo);
        String address2 = userRepository.findAddress(callNo);
        String message = callMessage;//name2 +" さん！！\nお待たせしました！！\nご注文いただいた商品が完成いたしました。\n本店までお越しください。";
        //int resultTest = userRepository.goLogin(username,pass);
        MailUtil(address2,name2,message);
        
        model.addAttribute("name", name2);
        System.out.println("　");
        System.out.println("名前:"+name2);
        System.out.println("アドレス:"+address2);
        //System.out.println("ID:"+codeid);
        //System.out.println("ID:"+callNo);
        System.out.println("注文者にメッセージを送りました。");
        System.out.println("テスト送信の場合はGmailのメールリストを確認してください。");
        //System.out.println("◎タイトル:"+message);
        System.out.println("◎メッセージ内容\nタイトル:"+subMessage+"\n内容:"+message);

        //if(resultTest >= 1) {
        return "call";
        //}else {
        //    return "miss";
        //}
    }
    
    public void MailUtil(String address, String name, String message) {
        //mailSender = mailSender;

        sendMail(address, name,message);
    }
    
    public void sendMail(String address, String name, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("pre-order-system@team6kk.jp"); // 送信元メールアドレス
        mailMessage.setTo(address); // 送信先メールアドレス
        //mailMessage.setCc(/* ccに入れるメールアドレス */);
        //mailMessage.setBcc(/* bccに入れるメールアドレス */);
        mailMessage.setSubject(subMessage);
        mailMessage.setText(message);

        /*
         try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            // TODO: エラー処理
        }
         */
    }
    
  //フォーム設定変更
    @RequestMapping(path = "/change", method = RequestMethod.GET)
    public String display7(Model model) {
        return "call4";
    }
    @RequestMapping(path = "/change", method = RequestMethod.POST)
    public String display7(String itemname1,String itemname2,String itemname3,Model model) {
        //List<Map<String, Object>> resultList = new ArrayList<>();

        //menuID = userRepository.sort();
        menuID = userRepository.goid2();
        int e = userRepository.menuChange(itemname1,menuID);
        
        //Map<String, Object> map1 = new HashMap<>();
        //map1.put("itemname", itemname1);
        //resultList.add(map1);

        //Map<String, Object> map2 = new HashMap<>();
        //map2.put("itemname", itemname2);
        //resultList.add(map2);

        //Map<String, Object> map3 = new HashMap<>();
        //map3.put("itemname", itemname3);
        //resultList.add(map3);
        //menuID = menuID;
        System.out.println("リストの数:"+menuID);
        //DBを使用する
        List<Map<String, Object>> resultList = userRepository.sort(quantity);
        model.addAttribute("resultList", resultList);
        return "call4";
    }
    
  //選択肢リセット
    @RequestMapping(path = "/reset", method = RequestMethod.GET)
    public String display8(Model model) {
      //注文情報を全削除する
        int resultList = userRepository.deletemenu();
        resultList = userRepository.insertmenu();
        menuID = userRepository.goid2();//menuID = 3; //IDをリセットする。
        System.out.println("リセット！！");
        //model.addAttribute("resultList", resultList);
        return "reset";
    }
    
  //呼び出しメール内容変更
    @RequestMapping(path = "/callar", method = RequestMethod.GET)
    public String display9(Model model) {
        return "call2";
    }
    @RequestMapping(path = "/callar", method = RequestMethod.POST)
    public String display9(String  story2,String mailtitle, Model model) {

        callMessage = story2; //呼び出しメッセージ変更
        subMessage = mailtitle;
        System.out.println("\n◎メッセージを変更しました\nタイトル:"+mailtitle+"\n内容:"+story2);

        return "call2";
    }
    
  //登録商品リスト画面
    @GetMapping("/list2")
    //private String display10(){
    //    return "list2.html";
    //}
    @RequestMapping(path = "/list2", method = RequestMethod.GET)
    public String display10(Model model){
        
        //DBを使用する
        List<Map<String, Object>> resultList = userRepository.findAll2();
        model.addAttribute("resultList", resultList);
        model.addAttribute("quantity", quantity);
        
        return "list2";
    }
    
  //並び替え用
    @RequestMapping(path = "/call3", method = RequestMethod.GET)
    public String display11(Model model){
        return "call3";
    }
    @RequestMapping(path = "/call3", method = RequestMethod.POST)
    public String display11(int sortNo,Model model) {

      //DBを使用する
        int resultList = userRepository.itemSort(sortNo);
        model.addAttribute("resultList", resultList);
        model.addAttribute("name", sortNo);
        //System.out.println("　");
        //System.out.println("名前:"+name2);
        //System.out.println("アドレス:"+address2);
        //System.out.println("ID:"+codeid);
        //System.out.println("ID:"+callNo);
        //System.out.println("注文者にメッセージを送りました。");
        //System.out.println("テスト送信の場合はGmailのメールリストを確認してください。");
        //System.out.println("◎タイトル:"+message);
        //System.out.println("◎メッセージ内容\nタイトル:"+subMessage+"\n内容:"+message);

        //if(resultTest >= 1) {
        return "call3";
        //}else {
        //    return "miss";
        //}
    }
    
  //並び替え用
    @RequestMapping(path = "/call5", method = RequestMethod.GET)
    public String display12(Model model){
        return "call5";
    }
    @RequestMapping(path = "/call5", method = RequestMethod.POST)
    public String display12(int number2,Model model) {

        quantity = number2; //数量変更
      //DBを使用する
        //int resultList = userRepository.itemSort(sortNo);
        //model.addAttribute("resultList", resultList);
        //model.addAttribute("name", sortNo);
        //System.out.println("　");
        //System.out.println("名前:"+name2);
        //System.out.println("アドレス:"+address2);
        //System.out.println("ID:"+codeid);
        //System.out.println("ID:"+callNo);
        //System.out.println("注文者にメッセージを送りました。");
        //System.out.println("テスト送信の場合はGmailのメールリストを確認してください。");
        //System.out.println("◎タイトル:"+message);
        //System.out.println("◎メッセージ内容\nタイトル:"+subMessage+"\n内容:"+message);

        //if(resultTest >= 1) {
        return "call5";
        //}else {
        //    return "miss";
        //}
    }
    
    //int id;
    
    //String station;
    
    //String prefecture;
    
    //int itemgenka;
    
  //画面表示用メソッド
    //@RequestMapping(path = "/result", method = RequestMethod.GET)
    //public String first(Model model) {

        //商品テーブルの全件検索
        //検索結果をmodelに格納.格納する際の名前は「resultList」とする。
        //List<Ormitem> resultList = ormitemRepository.findAll();
        
        //List<Routesearch> stationList =  fetch("https://mixway.ekispert.jp/v1/json/station/light?key=test_QMMh4JZJgnC&name="+search.value);
        
        //model.addAttribute("resultList", stationList);
        
        //return "result";
    //}
    
    //商品検索用メソッド
    //@RequestMapping(path = "/ormitem/search", method = RequestMethod.POST)
        //public String firstPost(Model model,String station) {
        
            //画面から入力された商品名であいまい検索をする。
            //ヒントRepositoryにメソッドを独自定義する必要あり。
        //String itemmember = null;
        //String itemboss = null;
        //画面から入力された会社名であいまい検索をする。
        //ヒントRepositoryにメソッドを独自定義する必要あり。
        //List<Ormitem> resultList = ormitemRepository.findByItemnameLike(itemname);
        //検索結果をmodelに格納する。
        //model.addAttribute("resultList", resultList);
        
            
            //検索結果をmodelに格納する。
            
        //return "ormitem";
    //}
    
}

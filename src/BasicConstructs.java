// Iteration 
  // List   
  List<String> list = new ArrayList<>() //Declare
  list = List.of("s1", "s2", "s3"); //Initialize   
  list.add("s4");
  list.remove("s4");
  list.size(); //size
  
  for(String s : list) { //Iterate
    System.out.println(s);
  }

  //Maps
  Map<String, String> map = new HashMap<>(); //declare
  Map<String, String> map = Map.of(
      "key1", "value1",
      "key2", "value2",
      "key3", "value3"
  );
  map.add("key4", "value4");
  map.remove("key4");
  map.size();

  for(Entry<String, String> : map){
  }

  for(String key : map.keySet){
     String value = map.get(key);
  }

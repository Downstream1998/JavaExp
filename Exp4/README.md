# 实验四：数据库编程——书目信息管理

## 实验目的

- 掌握数据库编程技术。
- 能正确连接数据库。
- 能对数据库中的信息进行查询、插入、删除、修改。

## 实验内容

1. 在数据库中创建一张书目信息表，包括书名、作者、出版社、出版日期、书号、价格字段。
2. 设计一个 GUI 界面进行书目管理。
   1. 在该界面上有四个选项卡，分别是查询、插入、删除、修改。
   2. 点击查询选项卡：
      - 出现的界面上有书名、作者、出版社、书号四个文本框，一个按钮和一个只读文本区。
      - 文本框内容可以为空，输入相应的查询信息后（例如根据书名查询可以仅输入书名），点击界面上的“查询”按钮，可以在界面下方的文本区中显示出符合条件的书目详细信息。
   3. 点击插入选项卡：
      - 出现的界面上有书名、作者、出版社、出版日期、书号、价格文本框，一个按钮。
      - 在文本框中输入信息后，点击“插入”按钮，该书目信息插入数据库表中。
   4. 点击删除选项卡：
      - 出现的界面上有书名文本框和一个按钮。
      - 输入书名后点击“删除”按钮，该书目信息从数据库表中删除。
   5. 点击修改选项卡：
      - 出现的界面上有书名、作者、出版社、出版日期、书号、价格文本框，一个按钮。
      - 输入的书名必须是已存在的，否则会弹出消息框显示出错信息。
      - 输入信息后，点击“修改”按钮，数据库表中的相应书目信息被修改为新值。
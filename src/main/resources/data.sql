
-- ユーザテーブルデータ
INSERT INTO users(user_name,email,password) VALUES('田中太郎', 'tanaka@aaa.com','himitu');
INSERT INTO users(user_name,email,password) VALUES('鈴木一郎', 'suzuki@aaa.com','himitu');
INSERT INTO users(user_name,email,password) VALUES('test', 'test','test');
INSERT INTO users(user_name,email,password) VALUES('test2', 'test2','test2');

-- カテゴリーテーブルデータ
--田中太郎のもの
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('食費', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('日用品', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('交通費', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('交際費', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('水道光熱費', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('住居費', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('通信費', FALSE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('娯楽費', FALSE, '#FF0000FF', 1);

INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('給与', TRUE, '#FF0000FF', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('副業', TRUE, '#FF0000FF', 1);


-- 項目テーブルデータ
--　INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('食事代', 1, 7, 1200, '2026/04/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('家賃', 1, 6, 62000, '2026/04/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('スマホ代', 1, 7, 4500, '2026/04/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('ランチ', 1, 1, 800, '2026/04/02','コンビニで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('買い出し', 1, 1, 5000, '2026/04/03','○○スーパーで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('買い出し', 1, 2, 700, '2026/04/06','トイレットペーパーとか');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('ランチ', 1, 1, 1200, '2026/04/08');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('推し', 1, 8, 5200, '2026/04/10');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('電車', 1, 3, 400, '2026/04/13');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('飲み会', 1, 4, 4200, '2026/04/13');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('買い出し', 1, 1, 5000, '2026/04/15','○○スーパーで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('電気代', 1, 5, 6200, '2026/04/16');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('温泉', 1, 8, 1700, '2026/04/18');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('バス', 1, 3, 400, '2026/04/21');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('ディナー', 1, 1, 1300, '2026/04/02','○○レストランにて');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('お給料', 1, 9, 220000, '2026/04/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('アプリ代', 1, 10, 5600, '2026/04/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('ガス代', 1, 5, 4200, '2026/04/26');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('シャンプー', 1, 2, 700, '2026/04/27');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('プレゼント', 1, 4, 4500, '2026/04/30','誕生日');

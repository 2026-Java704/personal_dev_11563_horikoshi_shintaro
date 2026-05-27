
-- ユーザテーブルデータ
INSERT INTO users(user_name,email,password) VALUES('田中次郎', 'tanaka@bbb.com','himitu');
INSERT INTO users(user_name,email,password) VALUES('鈴木一郎', 'suzuki@aaa.com','himitu');
INSERT INTO users(user_name,email,password) VALUES('田中太郎', 'tanaka@aaa.com','himitu');

-- カテゴリーテーブルデータ
--田中太郎
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('食費', FALSE, '#ef476f', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('日用品', FALSE, '#f78c6b', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('交通費', FALSE, '#ffd166', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('交際費', FALSE, '#83d483', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('水道光熱費', FALSE, '#06d6a0', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('住居費', FALSE, '#0cb0a9', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('通信費', FALSE, '#118ab2', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('娯楽費', FALSE, '#073b4c', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('給与', TRUE, '#baed00', 1);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('副業', TRUE, '#dbf679', 1);

--鈴木一郎
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('食費', FALSE, '#01befe', 2);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('日用品', FALSE, '#ffdd00', 2);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('交通費', FALSE, '#ff7d00', 2);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('交際費', FALSE, '#ff006d', 2);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('サブスク', FALSE, '#adff02', 2);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('趣味', FALSE, '#8f00ff', 2);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('バイト代', TRUE, '#322e33', 2);

--田中次郎
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('食費', FALSE, '#0f8bff', 3);
INSERT INTO genres(genre_name, is_income, color, user_id) VALUES('給与', TRUE, '#ff2424', 3);

-- 項目テーブルデータ
--田中太郎
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('家賃', 1, 6, 62000, '2026/05/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('スマホ代', 1, 7, 4500, '2026/05/01');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('ランチ', 1, 1, 800, '2026/05/02','コンビニで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('買い出し', 1, 1, 5000, '2026/05/03','○○スーパーで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('買い出し', 1, 2, 700, '2026/05/06','トイレットペーパーとか');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('ランチ', 1, 1, 1200, '2026/05/08');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('推し', 1, 8, 5200, '2026/05/10');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('電車', 1, 3, 400, '2026/05/13');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('飲み会', 1, 4, 4200, '2026/05/13');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('買い出し', 1, 1, 5000, '2026/05/15','○○スーパーで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('電気代', 1, 5, 6200, '2026/05/16');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('温泉', 1, 8, 1700, '2026/05/18');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('バス', 1, 3, 400, '2026/05/21');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('ディナー', 1, 1, 1300, '2026/05/02','○○レストランにて');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('お給料', 1, 9, 220000, '2026/05/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('アプリ代', 1, 10, 5600, '2026/05/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('ガス代', 1, 5, 4200, '2026/05/26');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('シャンプー', 1, 2, 700, '2026/05/27');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('プレゼント', 1, 4, 4500, '2026/05/30','誕生日');

--鈴木一郎
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('お弁当', 2, 11, 700, '2026/05/03','コンビニで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('本', 2, 16, 1200, '2026/05/04');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('レンタカー', 2, 13, 5300, '2026/05/07');
INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('飲み会', 2, 14, 3000, '2026/05/10','旧友と');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('ノートとペン', 2, 12, 1000, '2026/05/11');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('ゲーム', 2, 16, 5200, '2026/05/15');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('本', 2, 16, 1200, '2026/05/20');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('お給料', 2, 17, 60000, '2026/05/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('AI', 2, 15, 1400, '2026/05/25');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('新幹線', 2, 13, 21000, '2026/05/29');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('夕食', 2, 11, 1600, '2026/05/29');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('夕食', 2, 11, 1900, '2026/05/30');


INSERT INTO items(item_name, user_id, genre_id, price,add_date, comment) VALUES('お弁当', 3, 18, 800, '2026/05/15','コンビニで購入');
INSERT INTO items(item_name, user_id, genre_id, price,add_date) VALUES('バイト代', 3, 19, 40000, '2026/05/25');





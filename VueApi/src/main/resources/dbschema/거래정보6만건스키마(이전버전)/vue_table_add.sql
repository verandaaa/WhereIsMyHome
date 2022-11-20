# housedeal 테이블에 dealDate 칼럼 추가 및 dealYear, dealMonth, dealDay 이용 값 추가 

ALTER TABLE `ssafyvue`.`housedeal` 
ADD COLUMN `dealDate` DATE NULL AFTER `rentMoney`;

update housedeal set dealdate = str_to_date(concat(dealYear, '-', dealMonth, '-', dealDay), '%Y-%m-%d');
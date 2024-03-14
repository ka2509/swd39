INSERT INTO `user` (`email`, `fullName`, `password`, `role`)
VALUES
('duckhieu@gmail.com', 'Duc Khieu', '$2a$10$VIZyzXCUTTSst.Mifv5Vvep9J0I5OHs94h7iDtfMvDjTAyizPZ152', 'doctor'),
('duckhieu1@gmail.com', 'Duc Khieu', '$2a$10$VIZyzXCUTTSst.Mifv5Vvep9J0I5OHs94h7iDtfMvDjTAyizPZ152', 'doctor'),
('duckhieu2@gmail.com', 'Duc Khieu', '$2a$10$VIZyzXCUTTSst.Mifv5Vvep9J0I5OHs94h7iDtfMvDjTAyizPZ152', 'employee'),
('duckhieu3@gmail.com', 'Duc Khieu', '$2a$10$VIZyzXCUTTSst.Mifv5Vvep9J0I5OHs94h7iDtfMvDjTAyizPZ152', 'employee'),
('duckhieu4@gmail.com', 'Duc Khieu', '$2a$10$VIZyzXCUTTSst.Mifv5Vvep9J0I5OHs94h7iDtfMvDjTAyizPZ152', 'patient')
;
INSERT INTO `swd39`.`services` (`serviceDetails`, `serviceTitle`) VALUES ('kham suong suong', 'tai mui hong');
INSERT INTO `swd39`.`services` (`serviceDetails`, `serviceTitle`) VALUES ('kham suong suong', 'chinh hinh');
INSERT INTO `swd39`.`services` (`serviceDetails`, `serviceTitle`) VALUES ('kham suong suong', 'phau thuat tham my');
INSERT INTO `swd39`.`services` (`serviceDetails`, `serviceTitle`) VALUES ('kham suong suong', 'rang ham mieng');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('8h', '7h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('9h', '8h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('10h', '9h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('11h', '10h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('14h', '13h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('15h', '14h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('16h', '15h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('17h', '16h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('18h', '17h');
INSERT INTO `swd39`.`timeslots` (`endAt`, `startAt`) VALUES ('19h', '18h');

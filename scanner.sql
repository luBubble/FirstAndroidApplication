/*
Navicat MySQL Data Transfer

Source Server         : gz-cdb-k10o6lls.sql.tencentcdb.com_63067
Source Server Version : 50628
Source Host           : gz-cdb-k10o6lls.sql.tencentcdb.com:63067
Source Database       : scanner

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-05-25 15:04:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `Exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `Exam_name` varchar(255) DEFAULT NULL,
  `Exam_teacherId` int(11) DEFAULT NULL,
  `Exam_time` datetime DEFAULT NULL,
  PRIMARY KEY (`Exam_id`),
  KEY `exam_ibfk_1` (`Exam_teacherId`),
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`Exam_teacherId`) REFERENCES `teacher` (`Teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `Grade__id` int(11) NOT NULL AUTO_INCREMENT,
  `Grade_studentId` int(11) DEFAULT NULL,
  `Grade_teacherId` int(11) DEFAULT NULL,
  `Grade_examId` int(11) DEFAULT NULL,
  `Grade_wrong` varchar(255) DEFAULT NULL,
  `Grade_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`Grade__id`),
  KEY `grade_ibfk_1` (`Grade_studentId`),
  KEY `grade_ibfk_2` (`Grade_teacherId`),
  KEY `grade_ibfk_3` (`Grade_examId`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`Grade_studentId`) REFERENCES `student` (`Student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`Grade_teacherId`) REFERENCES `teacher` (`Teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grade_ibfk_3` FOREIGN KEY (`Grade_examId`) REFERENCES `exam` (`Exam_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `Relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `Relation_status` int(11) DEFAULT NULL,
  `Relation_state` int(11) DEFAULT NULL,
  `Relation_studentId` int(11) DEFAULT NULL,
  `Relation_teacherId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Relation_id`),
  KEY `relation_ibfk_1` (`Relation_studentId`),
  KEY `relation_ibfk_2` (`Relation_teacherId`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`Relation_studentId`) REFERENCES `student` (`Student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`Relation_teacherId`) REFERENCES `teacher` (`Teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `School_id` int(11) NOT NULL AUTO_INCREMENT,
  `School_num` int(11) DEFAULT NULL,
  `School_name` varchar(255) DEFAULT NULL,
  `School_account` varchar(255) DEFAULT NULL,
  `School_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`School_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Student_id` int(11) NOT NULL AUTO_INCREMENT,
  `Student_schoolId` int(11) DEFAULT NULL,
  `Student_teacherId` int(11) DEFAULT NULL,
  `Student_num` varchar(11) DEFAULT NULL,
  `Student_schname` varchar(255) DEFAULT NULL,
  `Student_class` varchar(255) DEFAULT NULL,
  `Student_name` varchar(255) DEFAULT NULL,
  `Student_age` int(11) DEFAULT NULL,
  `Student_sex` varchar(255) DEFAULT NULL,
  `Student_account` varchar(255) NOT NULL,
  `Student_password` varchar(255) NOT NULL,
  `Student_tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Student_id`),
  KEY `student_ibfk_1` (`Student_teacherId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Student_teacherId`) REFERENCES `teacher` (`Teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `Teacher_schoolId` int(11) DEFAULT NULL,
  `Teacher_tel` varchar(255) DEFAULT NULL,
  `Teacher_account` varchar(255) NOT NULL,
  `Teacher_password` varchar(255) NOT NULL,
  `Teacher_name` varchar(255) DEFAULT NULL,
  `Teacher_num` varchar(255) DEFAULT NULL,
  `Teacher_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2021 at 07:42 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `papeleria_estrella`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Nueva_Compra` (IN `Codigo_P` VARCHAR(11), IN `Codigo_Pro` VARCHAR(6), IN `Fecha_C` DATE, IN `Cantidad_C` INT(11), IN `Precio_C` DOUBLE, IN `ExistenciaMod` BIT)  BEGIN
DECLARE Total_C double DEFAULT 0;
IF Cantidad_C > 0 THEN 
    IF Codigo_Pro != '0' THEN 
        IF Codigo_P != '0' THEN
            SET Total_C = Precio_C * Cantidad_C;
            INSERT INTO proveedor_productos    VALUES(Codigo_P, Codigo_Pro, Fecha_C, Cantidad_C, Precio_C, Total_C, ExistenciaMod);
        END IF;
    END IF;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Nueva_Venta` (IN `Codigo_V` VARCHAR(8), IN `Fecha_V` DATE, IN `Total_V` DOUBLE, IN `Numero_Cl` INT(11), IN `Importe_V` DOUBLE)  BEGIN 

DECLARE Cambio_V double DEFAULT 0; 

IF (Importe_V > Total_V OR Importe_V = Total_V) THEN 

    SET Cambio_V = Importe_V - Total_V; 

    INSERT INTO venta VALUES(Codigo_V, Fecha_V, Total_V); 

    INSERT INTO cliente_venta VALUES(Codigo_V, Numero_Cl, Importe_V, Cambio_V); 

END IF; 

END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `CambioCliente` (`Codigo_V` VARCHAR(4)) RETURNS DOUBLE BEGIN 

DECLARE cam double; 

SELECT cv.Importe_V -TotalVenta(cv.Codigo_V) into cam from cliente_venta as cv where Codigo_V=cv.Codigo_V; 

Return cam; 

END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `TotalVenta` (`Codigo_V` VARCHAR(4)) RETURNS INT(11) BEGIN 

DECLARE SubTotalP int; 

DECLARE SubTotalS int; 

DECLARE Total int; 

SELECT SUM(p.Precio_P*pv.Cantidad_P) into SubTotalP from producto_venta as pv inner join producto as p on p.Codigo_P = pv.Codigo_P inner join venta as v on v.Codigo_V = pv.Codigo_V WHERE Codigo_V=v.Codigo_V; 

SELECT SUM(s.Precio_S*sv.Cantidad_S) into SubTotalS from servicio_venta as sv inner join servicio as s on s.Codigo_S = sv.Codigo_S inner join venta as vs on vs.Codigo_V=sv.Codigo_V  WHERE Codigo_V=vs.Codigo_V; 

Select SubTotalP + SubTotalS into Total;  

Return Total; 

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bitacora`
--

CREATE TABLE `bitacora` (
  `id` int(11) NOT NULL,
  `operacion` varchar(20) DEFAULT NULL,
  `usuario` varchar(40) DEFAULT NULL,
  `host` varchar(30) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `tabla` varchar(40) NOT NULL,
  `valoranterior` varchar(80) DEFAULT NULL,
  `valornuevo` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bitacora`
--

INSERT INTO `bitacora` (`id`, `operacion`, `usuario`, `host`, `fecha`, `tabla`, `valoranterior`, `valornuevo`) VALUES
(7, 'INSERTAR', 'eduardo', 'localhost', '2021-05-30 15:45:10', 'venta', '', 'V22 - 2021-05-30 - 27'),
(8, 'INSERTAR', 'eduardo', 'localhost', '2021-05-30 15:46:23', 'venta', '', 'V23 - 2021-05-30 - 24');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `Numero_Cl` int(11) NOT NULL,
  `Nombre_Cl` varchar(50) DEFAULT NULL,
  `Telefono_Cl` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`Numero_Cl`, `Nombre_Cl`, `Telefono_Cl`) VALUES
(0, NULL, NULL),
(1, 'Elias Gijon Vazquez', '8995062750'),
(2, 'Mario Hernandez Salazar', '8997052718'),
(3, 'Luciana Melendes Coronado', '8995067546'),
(4, 'Federico Gonzales Herrera', '8993075645'),
(5, 'Jose Luis Masias Rueda', '8996072445'),
(6, 'Pedro Medina', '8993974800'),
(7, 'Carlos Cortes', '8996340908'),
(8, 'Martin Vazquez', '8998540910'),
(9, 'Ramiro Gomez', '8997548907'),
(10, 'Maria Hernandez', '8991542635'),
(11, 'Ana Garza', '8998451526'),
(12, 'Josue Villareal', '8996589584'),
(13, 'Mario', '8991920000'),
(14, 'Enrique', '8990000001');

--
-- Triggers `cliente`
--
DELIMITER $$
CREATE TRIGGER `ultimo_Numero_Cl` AFTER INSERT ON `cliente` FOR EACH ROW BEGIN
UPDATE ultimas_claves_secuenciales set Numero_Cl = new.Numero_Cl;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cliente_venta`
--

CREATE TABLE `cliente_venta` (
  `Codigo_V` varchar(8) DEFAULT NULL,
  `Numero_Cl` int(11) DEFAULT NULL,
  `Importe_V` double DEFAULT NULL,
  `Cambio_V` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente_venta`
--

INSERT INTO `cliente_venta` (`Codigo_V`, `Numero_Cl`, `Importe_V`, `Cambio_V`) VALUES
('V1', 1, 100, 54),
('V2', 2, 20, 0),
('V3', 3, 100, 32),
('V4', 4, 50, 7),
('V5', 5, 50, 30),
('V6', 6, 200, 62),
('V7', 7, 100, 25),
('V8', 8, 100, 13),
('V9', 9, 100, 45),
('V10', 10, 100, 42),
('V11', 11, 200, 157),
('V14', 14, 200, 35);

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `Codigo_P` varchar(11) NOT NULL,
  `NOMBRE_P` varchar(50) DEFAULT NULL,
  `Existencia_P` int(11) DEFAULT NULL,
  `Precio_P` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`Codigo_P`, `NOMBRE_P`, `Existencia_P`, `Precio_P`) VALUES
('0', NULL, 0, 0),
('13111300755', 'DIST. FOMI 20 * 30 cm', 139, 5),
('13111308525', 'PLACA UNICEL 25*25*2 CM', 15, 10),
('14111606L21', 'PAPEL CASCARON 1/2 56*70 CM', 7, 20),
('14111616333', 'PAPEL LUSTRINA LUSTRE BANDERA', 15, 8),
('24111503066', 'BOLSA CAMISETA MEDIANA', 15, 29),
('41111604014', 'REGLA PLASTICO BACO 30CM', 23, 6),
('60121121418', 'PAPEL CARTULINA IMP. FLUOR ROSA 48*66 CM', 1, 9),
('60141001012', 'GLOBO PAYASO SURTIDO', 235, 6),
('60141001015', 'GLOBO DECORATOR#9 COLOR BLANCO', 145, 6),
('60141001022', 'GLOBO DECORATOR#9 COLOR NEGRO', 90, 6);

-- --------------------------------------------------------

--
-- Table structure for table `producto_venta`
--

CREATE TABLE `producto_venta` (
  `Codigo_V` varchar(8) DEFAULT NULL,
  `Codigo_P` varchar(11) DEFAULT NULL,
  `Cantidad_P` int(11) DEFAULT NULL,
  `Venta_Registrada` bit(1) DEFAULT NULL,
  `ExistenciaMod` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto_venta`
--

INSERT INTO `producto_venta` (`Codigo_V`, `Codigo_P`, `Cantidad_P`, `Venta_Registrada`, `ExistenciaMod`) VALUES
('V1', '60141001012', 5, b'1', b'1'),
('V1', '14111616333', 2, b'1', b'1'),
('V10', '14111606L21', 2, b'1', b'1'),
('V11', '60121121418', 2, b'1', b'1'),
('V14', '13111308525', 12, b'1', b'1'),
('V2', '0', 0, b'1', b'1'),
('V3', '60121121418', 2, b'1', b'1'),
('V3', '60141001015', 5, b'1', b'1'),
('V4', '14111606L21', 1, b'1', b'1'),
('V5', '0', 0, b'1', b'1'),
('V6', '60121121418', 2, b'1', b'1'),
('V6', '60141001022', 10, b'1', b'1'),
('V6', '13111308525', 3, b'1', b'1'),
('V9', '13111300755', 5, b'1', b'1'),
('V7', '60141001012', 10, b'1', b'1'),
('V8', '41111604014', 2, b'1', b'1'),
('V22', '60121121418', 3, b'1', b'1'),
('V23', '14111616333', 3, b'1', b'1');

--
-- Triggers `producto_venta`
--
DELIMITER $$
CREATE TRIGGER `Act_Existencia_P` AFTER INSERT ON `producto_venta` FOR EACH ROW BEGIN
UPDATE producto SET Existencia_P = Existencia_P - NEW.cantidad_P WHERE producto.Codigo_P = NEW.Codigo_P;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `proveedor`
--

CREATE TABLE `proveedor` (
  `Codigo_Pro` varchar(6) NOT NULL,
  `Nombre_Pro` varchar(50) DEFAULT NULL,
  `Direccion_Pro` varchar(50) DEFAULT NULL,
  `No_Tel_Pro` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proveedor`
--

INSERT INTO `proveedor` (`Codigo_Pro`, `Nombre_Pro`, `Direccion_Pro`, `No_Tel_Pro`) VALUES
('0', NULL, NULL, NULL),
('ABAH1', 'ABASTECEDORA DE MERCERIAS, S.A DE C.V.', 'BLVD HIDALGO 1675 LOC 2B REYNOSA, TAMPS', '8999241222'),
('CGR6Y3', 'COMERCIAL GARZA REYNA, S.A. DE C.V.', '5 FEBRERO 516 SAN RICARDO, REYNOSA TAMAULIPAS', '8180042000'),
('INID9', 'Inix Comercial, S.A. de C.V.', 'MARIANO MATAMOROS NO. 304, COL. TLALPAN', '8991287265'),
('JUAG0', 'JUAN ANTELMO MARTINEZ MARTINEZ', 'PUEBLA 214 SOLIDARIDAD, REYNOSA', '8991287265'),
('MARFM4', 'MAR DISTRIBUIDORES', 'TLAXCALA 99 SOLIDARIDAD, REYNOSA TAMAULIPAS', '8991287265');

-- --------------------------------------------------------

--
-- Table structure for table `proveedor_productos`
--

CREATE TABLE `proveedor_productos` (
  `Codigo_P` varchar(11) DEFAULT NULL,
  `Codigo_Pro` varchar(6) DEFAULT NULL,
  `Fecha_C` date DEFAULT NULL,
  `Cantidad_C` int(11) DEFAULT NULL,
  `Precio_C` double DEFAULT NULL,
  `Total_C` double DEFAULT NULL,
  `ExistenciaMod` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proveedor_productos`
--

INSERT INTO `proveedor_productos` (`Codigo_P`, `Codigo_Pro`, `Fecha_C`, `Cantidad_C`, `Precio_C`, `Total_C`, `ExistenciaMod`) VALUES
('13111300755', 'ABAH1', '2020-12-17', 144, 1.117, 168.8, b'1'),
('13111308525', 'CGR6Y3', '2020-10-20', 10, 2.15, 21.5, b'1'),
('13111308525', 'CGR6Y3', '2020-12-20', 10, 2.5, 25, b'1'),
('13111308525', 'CGR6Y3', '2020-12-23', 10, 2.5, 25, b'1'),
('14111606L21', 'CGR6Y3', '2020-10-20', 10, 8.33, 83.3, b'1'),
('14111616333', 'CGR6Y3', '2020-10-20', 20, 1.6, 32.1, b'1'),
('24111503066', 'JUAG0', '2020-11-08', 3, 25.03, 75.09, b'1'),
('41111604014', 'CGR6Y3', '2020-11-27', 25, 2.43, 60.9, b'1'),
('60121121418', 'CGR6Y3', '2020-10-20', 10, 3.65, 36.5, b'1'),
('60141001012', 'ABAH1', '2020-11-07', 250, 0.56, 140, b'1'),
('60141001015', 'MARFM4', '2020-11-07', 150, 0.56, 84, b'1'),
('60141001022', 'MARFM4', '2020-11-07', 100, 0.56, 56, b'1'),
('24111503066', 'CGR6Y3', '2021-05-30', 10, 5, 50, b'0'),
('24111503066', 'ABAH1', '2021-05-30', 5, 20, 100, b'0');

--
-- Triggers `proveedor_productos`
--
DELIMITER $$
CREATE TRIGGER `Comprar_producto` AFTER INSERT ON `proveedor_productos` FOR EACH ROW BEGIN
    UPDATE producto SET Existencia_P = Existencia_P + NEW.Cantidad_C WHERE producto.Codigo_P = NEW.Codigo_P;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `servicio`
--

CREATE TABLE `servicio` (
  `Codigo_S` varchar(4) NOT NULL,
  `Nombre_S` varchar(50) DEFAULT NULL,
  `Precio_S` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `servicio`
--

INSERT INTO `servicio` (`Codigo_S`, `Nombre_S`, `Precio_S`) VALUES
('0', NULL, 0),
('CDLU', 'CORTE DE LETRAS DE UNICEL', 20),
('DCCS', 'DECORACIONES', 15),
('ENVT', 'ENVOLTURAS DE REGALO', 15),
('LLDG', 'LLENADO DE GLOBOS/C HELIO', 75),
('MNLD', 'MANUALIDADES, ETC', 25),
('MPFI', 'MARCO PARA FOTO INFANTILES DE UNICEL', 50),
('MQTA', 'ELABORACION DE MAQUETA', 10),
('SCBN', 'COPIAS BLANCO Y NEGRO', 2),
('SCCO', 'COPIAS A COLOR', 5),
('SENG', 'ENGARGOLADO', 10),
('SENM', 'ENMICADO', 12);

-- --------------------------------------------------------

--
-- Table structure for table `servicio_venta`
--

CREATE TABLE `servicio_venta` (
  `Codigo_V` varchar(8) DEFAULT NULL,
  `Codigo_S` varchar(4) DEFAULT NULL,
  `Cantidad_S` int(11) DEFAULT NULL,
  `Ventas_S_Registrada` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `servicio_venta`
--

INSERT INTO `servicio_venta` (`Codigo_V`, `Codigo_S`, `Cantidad_S`, `Ventas_S_Registrada`) VALUES
('V1', '0', 0, b'0'),
('V10', 'SCCO', 4, b'0'),
('V11', 'MNLD', 1, b'0'),
('V14', 'ENVT', 3, b'0'),
('V2', 'SCBN', 5, b'0'),
('V2', 'SCCO', 2, b'0'),
('V3', 'SENG', 2, b'0'),
('V3', '0', 0, b'0'),
('V4', 'SENM', 2, b'0'),
('V5', 'SCCO', 4, b'0'),
('V6', '0', 0, b'0'),
('V6', 'ENVT', 2, b'0'),
('V7', 'ENVT', 1, b'0'),
('V8', 'LLDG', 1, b'0'),
('V9', 'SENG', 3, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `ultimas_claves_secuenciales`
--

CREATE TABLE `ultimas_claves_secuenciales` (
  `Codigo_V` varchar(8) DEFAULT NULL,
  `Numero_Cl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ultimas_claves_secuenciales`
--

INSERT INTO `ultimas_claves_secuenciales` (`Codigo_V`, `Numero_Cl`) VALUES
('V23', 14);

-- --------------------------------------------------------

--
-- Table structure for table `venta`
--

CREATE TABLE `venta` (
  `Codigo_V` varchar(8) NOT NULL,
  `Fecha_V` date DEFAULT NULL,
  `Total_V` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `venta`
--

INSERT INTO `venta` (`Codigo_V`, `Fecha_V`, `Total_V`) VALUES
('V1', '2020-11-11', 46),
('V10', '2020-12-19', 58),
('V11', '2020-12-20', 43),
('V14', '2021-02-12', 165),
('V2', '2020-11-11', 20),
('V22', '2021-05-30', 27),
('V23', '2021-05-30', 24),
('V3', '2020-11-12', 68),
('V4', '2020-11-12', 43),
('V5', '2020-11-13', 20),
('V6', '2020-11-13', 138),
('V7', '2020-12-17', 75),
('V8', '2020-12-18', 87),
('V9', '2020-12-19', 55);

--
-- Triggers `venta`
--
DELIMITER $$
CREATE TRIGGER `delete_Venta` AFTER DELETE ON `venta` FOR EACH ROW BEGIN
    INSERT INTO bitacora(operacion, usuario, host,  fecha, tabla, valoranterior, valornuevo) VALUES('delete', SUBSTRING(USER(), 1, (INSTR(USER(),'@') - 1)), SUBSTRING(USER(), (INSTR(USER(),'@')+1)), NOW(), 'venta', CONCAT(OLD.Codigo_V, ' - ', OLD.Fecha_V, ' - ', OLD.Total_V),   ' ');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertar_Nueva_Venta` AFTER INSERT ON `venta` FOR EACH ROW BEGIN
    INSERT INTO bitacora(operacion, usuario, host,  fecha, tabla, valoranterior, valornuevo)                VALUES('INSERTAR', SUBSTRING(USER(), 1, (INSTR(USER(), '@') - 1)), SUBSTRING(USER(), (INSTR(USER(),     '@')+1)), NOW(), 'venta', '', CONCAT(NEW.Codigo_V, ' - ', NEW.Fecha_V, ' - ',     NEW.Total_V));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `ultima_Venta` AFTER INSERT ON `venta` FOR EACH ROW BEGIN
    UPDATE ultimas_claves_secuenciales SET Codigo_V = NEW.Codigo_V;
    END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_Venta` AFTER UPDATE ON `venta` FOR EACH ROW BEGIN
    INSERT INTO bitacora(operacion, usuario, host,  fecha, tabla, valoranterior, valornuevo) VALUES('update', SUBSTRING(USER(), 1, (INSTR(USER(),'@') - 1)), SUBSTRING(USER(), (INSTR(USER(),'@')+1)), NOW(), 'venta', CONCAT(OLD.Codigo_V, ' - ', OLD.Fecha_V, ' - ', OLD.Total_V),  CONCAT(NEW.Codigo_V, ' - ', NEW.Fecha_V, ' - ', NEW.Total_V));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_clientes_contacto`
-- (See below for the actual view)
--
CREATE TABLE `vista_clientes_contacto` (
`Cliente` varchar(50)
,`Telefono` varchar(10)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_compras_2020`
-- (See below for the actual view)
--
CREATE TABLE `vista_compras_2020` (
`Cliente` varchar(50)
,`Telefono` varchar(10)
,`Fecha` date
,`Costo` double
,`Pago` double
,`Cambio` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_producto_proveedor`
-- (See below for the actual view)
--
CREATE TABLE `vista_producto_proveedor` (
`Producto` varchar(50)
,`Proveedor` varchar(50)
);

-- --------------------------------------------------------

--
-- Structure for view `vista_clientes_contacto`
--
DROP TABLE IF EXISTS `vista_clientes_contacto`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_clientes_contacto`  AS SELECT `c`.`Nombre_Cl` AS `Cliente`, `c`.`Telefono_Cl` AS `Telefono` FROM `cliente` AS `c` WHERE `c`.`Numero_Cl` < all (select `cliente`.`Nombre_Cl` from `cliente` where `cliente`.`Telefono_Cl` like '%899') AND `c`.`Nombre_Cl` is not null ;

-- --------------------------------------------------------

--
-- Structure for view `vista_compras_2020`
--
DROP TABLE IF EXISTS `vista_compras_2020`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_compras_2020`  AS SELECT `c`.`Nombre_Cl` AS `Cliente`, `c`.`Telefono_Cl` AS `Telefono`, `v`.`Fecha_V` AS `Fecha`, `v`.`Total_V` AS `Costo`, `cv`.`Importe_V` AS `Pago`, `cv`.`Cambio_V` AS `Cambio` FROM ((`cliente_venta` `cv` join `cliente` `c` on(`c`.`Numero_Cl` = `cv`.`Numero_Cl`)) join `venta` `v` on(`v`.`Codigo_V` = `cv`.`Codigo_V`)) HAVING `v`.`Fecha_V` <= '2020-12-31' ;

-- --------------------------------------------------------

--
-- Structure for view `vista_producto_proveedor`
--
DROP TABLE IF EXISTS `vista_producto_proveedor`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_producto_proveedor`  AS SELECT `p`.`NOMBRE_P` AS `Producto`, `pro`.`Nombre_Pro` AS `Proveedor` FROM ((`proveedor_productos` `pp` join `producto` `p` on(`p`.`Codigo_P` = `pp`.`Codigo_P`)) join `proveedor` `pro` on(`pp`.`Codigo_Pro` = `pro`.`Codigo_Pro`)) GROUP BY `p`.`NOMBRE_P` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bitacora`
--
ALTER TABLE `bitacora`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Numero_Cl`),
  ADD KEY `Nombre_Cl` (`Nombre_Cl`);

--
-- Indexes for table `cliente_venta`
--
ALTER TABLE `cliente_venta`
  ADD KEY `FK_Codigo_Venta_CV` (`Codigo_V`),
  ADD KEY `FK_Numero_Cliente_CV` (`Numero_Cl`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`Codigo_P`),
  ADD KEY `NOMBRE_P` (`NOMBRE_P`);

--
-- Indexes for table `producto_venta`
--
ALTER TABLE `producto_venta`
  ADD KEY `FK_Codigo_Venta_PV` (`Codigo_V`),
  ADD KEY `FK_Codigo_Producto_PV` (`Codigo_P`);

--
-- Indexes for table `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`Codigo_Pro`),
  ADD KEY `Nombre_Pro` (`Nombre_Pro`);

--
-- Indexes for table `proveedor_productos`
--
ALTER TABLE `proveedor_productos`
  ADD KEY `FK_Codigo_Proveedor_PP` (`Codigo_Pro`),
  ADD KEY `FK_Codigo_Producto_PP` (`Codigo_P`);

--
-- Indexes for table `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`Codigo_S`),
  ADD KEY `Nombre_S` (`Nombre_S`);

--
-- Indexes for table `servicio_venta`
--
ALTER TABLE `servicio_venta`
  ADD KEY `FK_Codigo_Venta_SV` (`Codigo_V`),
  ADD KEY `FK_Codigo_Servicio_SV` (`Codigo_S`);

--
-- Indexes for table `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`Codigo_V`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bitacora`
--
ALTER TABLE `bitacora`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cliente_venta`
--
ALTER TABLE `cliente_venta`
  ADD CONSTRAINT `FK_Codigo_Venta_CV` FOREIGN KEY (`Codigo_V`) REFERENCES `venta` (`Codigo_V`),
  ADD CONSTRAINT `FK_Numero_Cliente_CV` FOREIGN KEY (`Numero_Cl`) REFERENCES `cliente` (`Numero_Cl`);

--
-- Constraints for table `producto_venta`
--
ALTER TABLE `producto_venta`
  ADD CONSTRAINT `FK_Codigo_Producto_PV` FOREIGN KEY (`Codigo_P`) REFERENCES `producto` (`Codigo_P`),
  ADD CONSTRAINT `FK_Codigo_Venta_PV` FOREIGN KEY (`Codigo_V`) REFERENCES `venta` (`Codigo_V`);

--
-- Constraints for table `proveedor_productos`
--
ALTER TABLE `proveedor_productos`
  ADD CONSTRAINT `FK_Codigo_Producto_PP` FOREIGN KEY (`Codigo_P`) REFERENCES `producto` (`Codigo_P`),
  ADD CONSTRAINT `FK_Codigo_Proveedor_PP` FOREIGN KEY (`Codigo_Pro`) REFERENCES `proveedor` (`Codigo_Pro`);

--
-- Constraints for table `servicio_venta`
--
ALTER TABLE `servicio_venta`
  ADD CONSTRAINT `FK_Codigo_Servicio_SV` FOREIGN KEY (`Codigo_S`) REFERENCES `servicio` (`Codigo_S`),
  ADD CONSTRAINT `FK_Codigo_Venta_SV` FOREIGN KEY (`Codigo_V`) REFERENCES `venta` (`Codigo_V`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

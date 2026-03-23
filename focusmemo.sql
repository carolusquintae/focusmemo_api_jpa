SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `focusmemo`
--
CREATE DATABASE IF NOT EXISTS `focusmemo` 
DEFAULT CHARACTER SET utf8mb4 
COLLATE utf8mb4_spanish_ci;

USE `focusmemo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mazo`
--

DROP TABLE IF EXISTS `mazo`;
CREATE TABLE IF NOT EXISTS `mazo` (
    `id_mazo` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_usuario` bigint(20) NOT NULL,
    `nombre` varchar(150) NOT NULL,
    `descripcion` text,
    `color` varchar(20) DEFAULT NULL,
    `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_mazo`),
    KEY `fk_mazo_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida_minijuego`
--

DROP TABLE IF EXISTS `partida_minijuego`;
CREATE TABLE IF NOT EXISTS `partida_minijuego` (
    `id_partida` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_usuario` bigint(20) NOT NULL,
    `puntuacion` int(11) DEFAULT '0',
    `aciertos` int(11) DEFAULT '0',
    `errores` int(11) DEFAULT '0',
    `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_partida`),
    KEY `fk_partida_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `progreso_tarjeta`
--

DROP TABLE IF EXISTS `progreso_tarjeta`;
CREATE TABLE IF NOT EXISTS `progreso_tarjeta` (
    `id_progreso` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_usuario` bigint(20) NOT NULL,
    `id_tarjeta` bigint(20) NOT NULL,
    `repeticion` int(11) DEFAULT '0',
    `intervalo` int(11) DEFAULT '1',
    `ease_factor` double DEFAULT '2.5',
    `proxima_revision` date DEFAULT NULL,
    `ultima_revision` date DEFAULT NULL,
    PRIMARY KEY (`id_progreso`),
    UNIQUE KEY `unique_usuario_tarjeta` (`id_usuario`,`id_tarjeta`),
    KEY `fk_progreso_tarjeta` (`id_tarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultado_tarjeta`
--

DROP TABLE IF EXISTS `resultado_tarjeta`;
CREATE TABLE IF NOT EXISTS `resultado_tarjeta` (
    `id_resultado` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_usuario` bigint(20) NOT NULL,
    `id_tarjeta` bigint(20) NOT NULL,
    `calidad_respuesta` int(11) NOT NULL,
    `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_resultado`),
    KEY `fk_resultado_usuario` (`id_usuario`),
    KEY `fk_resultado_tarjeta` (`id_tarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion_estudio`
--

DROP TABLE IF EXISTS `sesion_estudio`;
CREATE TABLE IF NOT EXISTS `sesion_estudio` (
    `id_sesion` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_usuario` bigint(20) NOT NULL,
    `fecha_inicio` datetime DEFAULT NULL,
    `fecha_fin` datetime DEFAULT NULL,
    `tarjetas_estudiadas` int(11) DEFAULT '0',
    `aciertos` int(11) DEFAULT '0',
    `errores` int(11) DEFAULT '0',
    PRIMARY KEY (`id_sesion`),
    KEY `fk_sesion_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
CREATE TABLE IF NOT EXISTS `suscripcion` (
    `id_suscripcion` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_usuario` bigint(20) NOT NULL,
    `tipo_plan` varchar(50) DEFAULT NULL,
    `fecha_inicio` datetime DEFAULT NULL,
    `fecha_fin` datetime DEFAULT NULL,
    `estado` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id_suscripcion`),
    KEY `fk_suscripcion_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
CREATE TABLE IF NOT EXISTS `tarjeta` (
    `id_tarjeta` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_mazo` bigint(20) NOT NULL,
    `pregunta` text NOT NULL,
    `respuesta` text NOT NULL,
    `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
    `activa` tinyint(1) DEFAULT '1',
    PRIMARY KEY (`id_tarjeta`),
    KEY `fk_tarjeta_mazo` (`id_mazo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
    `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(100) NOT NULL,
    `email` varchar(150) NOT NULL,
    `password_hash` varchar(255) NOT NULL,
    `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
    `premium` tinyint(1) DEFAULT '0',
    `fecha_fin_premium` datetime DEFAULT NULL,
    PRIMARY KEY (`id_usuario`),
    UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mazo`
--
ALTER TABLE `mazo`
    ADD CONSTRAINT `fk_mazo_usuario` 
        FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `partida_minijuego`
--
ALTER TABLE `partida_minijuego`
    ADD CONSTRAINT `fk_partida_usuario` 
        FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `progreso_tarjeta`
--
ALTER TABLE `progreso_tarjeta`
    ADD CONSTRAINT `fk_progreso_tarjeta` 
        FOREIGN KEY (`id_tarjeta`) REFERENCES `tarjeta` (`id_tarjeta`) ON DELETE CASCADE,
    ADD CONSTRAINT `fk_progreso_usuario` 
        FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `resultado_tarjeta`
--
ALTER TABLE `resultado_tarjeta`
    ADD CONSTRAINT `fk_resultado_tarjeta` 
        FOREIGN KEY (`id_tarjeta`) REFERENCES `tarjeta` (`id_tarjeta`) ON DELETE CASCADE,
    ADD CONSTRAINT `fk_resultado_usuario` 
        FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `sesion_estudio`
--
ALTER TABLE `sesion_estudio`
    ADD CONSTRAINT `fk_sesion_usuario` 
        FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
    ADD CONSTRAINT `fk_suscripcion_usuario` 
        FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
    ADD CONSTRAINT `fk_tarjeta_mazo` 
        FOREIGN KEY (`id_mazo`) REFERENCES `mazo` (`id_mazo`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
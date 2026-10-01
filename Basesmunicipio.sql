SELECT * FROM digitalizacion_municipio.responsables;
CREATE INDEX idx_clave_expediente ON documento_municipal(clave_expediente);
CREATE INDEX idx_clasificacion ON documento_municipal(clasificacion_acceso);
CREATE INDEX idx_fase_ciclo ON documento_municipal(fase_ciclo_vida);
CREATE INDEX idx_materia ON archivo_municipal(tipo_materia);
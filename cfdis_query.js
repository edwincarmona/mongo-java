db.cfdis.find({"cfdi:Comprobante.Moneda": "MXN"})
   .limit(100);
   
db.cfdis.find({"cfdi:Comprobante.Moneda": "USD" })
.limit(100);
   
db.cfdis.find({"cfdi:Comprobante.cfdi:Complemento.tfd:TimbreFiscalDigital.UUID":{ $ne: "" }})
.limit(100);

db.cfdis.find({"cfdi:Comprobante.cfdi:Complemento.tfd:TimbreFiscalDigital.UUID":{ $exists: true }})
.limit(100);

db.cfdis.find({"cfdi:Comprobante.cfdi:Complemento.tfd:TimbreFiscalDigital.UUID":{ $exists: false }})
.limit(100);

   
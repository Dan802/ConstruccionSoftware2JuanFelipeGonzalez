package app.adapters.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.invoice.entity.InvoiceEntity;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
  public InvoiceEntity findByInvoiceId(Long invoiceId);
}

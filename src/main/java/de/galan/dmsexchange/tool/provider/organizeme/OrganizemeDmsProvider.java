package de.galan.dmsexchange.tool.provider.organizeme;

import static de.galan.commons.test.Tests.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;

import com.thoughtworks.xstream.XStream;

import de.galan.commons.logging.Slogr;
import de.galan.dmsexchange.DmsExchange;
import de.galan.dmsexchange.exchange.DmsWriter;
import de.galan.dmsexchange.meta.Document;
import de.galan.dmsexchange.meta.DocumentFile;
import de.galan.dmsexchange.meta.Revision;
import de.galan.dmsexchange.meta.Source;
import de.galan.dmsexchange.tool.provider.DmsProvider;


/**
 * Converts the proprietary format provided by organize.me to dms-exchange
 */
public class OrganizemeDmsProvider implements DmsProvider {

	private static final Logger LOG = Slogr.get();
	private static final Source SOURCE = new Source("organize.me GmbH", null, "https://www.organize.me", "feedback@organize.me");


	@Override
	public void exportArchive() {
		// Parameter
		File file = new File("/home/daniel/Dropbox/dev/git/dms-exchange-integration/instance/storage/organizeme-e6e6591e-3d4a-4d73-ab61-bb566950b6d5.zip");
		File target = new File(getTestDirectory(true), "export-archive.tgz");

		try {
			ZipFile zipfile = new ZipFile(file);
			List<OmDocument> omdocs = readDataXml(zipfile);

			try (DmsWriter writer = DmsExchange.createWriter(target)) {
				for (OmDocument omdoc: omdocs) {
					writer.add(convertDocument(zipfile, omdoc));
				}
			}
		}
		catch (Exception ex) {
			LOG.error("failed", ex);
		}
	}


	protected List<OmDocument> readDataXml(ZipFile zipfile) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("documents", OmDocuments.class);
		xstream.alias("document", OmDocument.class);
		xstream.ignoreUnknownElements();
		xstream.addImplicitCollection(OmDocuments.class, "documents");

		ZipEntry entry = zipfile.getEntry("data.xml");
		InputStream stream = zipfile.getInputStream(entry);
		return ((OmDocuments)xstream.fromXML(stream)).getDocuments();
	}


	protected Document convertDocument(ZipFile zipfile, OmDocument omdoc) throws IOException {
		ZipEntry entry = zipfile.getEntry(omdoc.getFile());
		InputStream stream = zipfile.getInputStream(entry);

		Document doc = new Document().source(SOURCE);
		DocumentFile docfile = new DocumentFile(omdoc.getTitle());
		docfile.addRevision(new Revision(omdoc.getCreatedTime()).data(stream));
		doc.addDocumentFile(docfile);
		doc.labels(omdoc.getTagsArray()).note(omdoc.getNotes()).idSystem(omdoc.getUuid()).project(omdoc.getCategory());
		return doc;
	}


	@Override
	public void importArchive() {
		// nada
	}

}

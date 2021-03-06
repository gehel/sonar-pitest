/*
 * Sonar Pitest Plugin
 * Copyright (C) 2009 Alexandre Victoor
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.pitest;

import static org.sonar.plugins.pitest.PitestConstants.*;

import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import com.google.common.collect.Lists;

/**
 * This class is the entry point for all PIT extensions
 */
@Properties({
  @Property(key = MODE_KEY, defaultValue = MODE_SKIP,
    name = "PIT activation mode", description = "Possible values:  empty (means skip) and 'reuseReport'", global = true,
    project = true),
  @Property(key = REPORT_DIRECTORY_KEY, defaultValue = REPORT_DIRECTORY_DEF,
    name = "Output directory for the PIT reports", description = "This property is needed when the 'reuseReport' mode is activated and the reports are not located in the default directory (i.e. target/pit-reports)", global = true,
    project = true)
})
public final class PitestPlugin extends SonarPlugin {

  // This is where you're going to declare all your Sonar extensions
  @SuppressWarnings("unchecked")
  public List<Class<? extends Extension>> getExtensions() {
    return Lists.newArrayList(
        ResultParser.class,
        ReportFinder.class,
        PitestRuleRepository.class,
        PitestSensor.class,
        PitestMetrics.class,
        PitestDecorator.class,
        PitestCoverageDecorator.class,
        PitestDashboardWidget.class,
        PitSourceTab.class
    );
  }
}
